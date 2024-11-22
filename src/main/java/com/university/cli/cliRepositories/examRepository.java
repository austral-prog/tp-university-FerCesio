package com.university.cli.cliRepositories;

import com.university.examTypes.FinalPracticalWork;
import com.university.examTypes.OralExam;
import com.university.examTypes.PracticalWork;
import com.university.examTypes.WrittenExam;
import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.university.cli.MainCLI.scanner;

public class examRepository implements CRUDRepository<Examination> {
    int id = 0;
    Class<Examination> entityClass;
    private final Subject subject;

    public examRepository(Class<Examination> entityClass, Subject subject) {
        this.entityClass = entityClass;
        this.subject = subject;
    }



    public Subject getSubject() {
        return subject;
    }

    public String getIdentifier(){
        return "Examination";
    }

    public Class<Examination> getEntityClass(){
        return entityClass;
    }

    public Examination examSelecter() {
        while (true) {
            System.out.println("Enter exam name: ");
            String examName = scanner.nextLine();
            System.out.println("Enter exam type: ");
            String examType = scanner.nextLine();
            Examination exam = null;
            for (Examination exam1 : getSubject().getExamList()) {
                if (exam1.getExamName().equals(examName) && exam1.getType().equals(examType)) {
                    exam = exam1;
                    break;
                }
            }

            if (exam != null) {
                return exam;
            } else {
                System.out.println("Invalid exam name or type. Please try again.");
            }
        }
    }

    public void createExam(){
        System.out.println("Enter exam name: ");
        String evaluationName = scanner.nextLine();
        Examination exam1 = null;
        while (exam1 == null) {
            System.out.println("Enter exam type: ");
            String evaluationType = scanner.nextLine();
            exam1 = switch (evaluationType) {
                case "WRITTEN_EXAM" -> new WrittenExam(evaluationName, evaluationType);
                case "ORAL_EXAM" -> new OralExam(evaluationName, evaluationType);
                case "PRACTICAL_WORK" -> new PracticalWork(evaluationName, evaluationType);
                case "FINAL_PRACTICAL_WORK" -> new FinalPracticalWork(evaluationName, evaluationType);
                default -> {
                    System.out.println("Invalid exam type. Please enter a valid exam type.");
                    yield null;
                }
            };
        }
        create(exam1);
        System.out.println("Exam created");
    }

    public void create(Examination entity){
        entity.setId(id);
        id++;
        subject.getExamList().add(entity);
    }

    public void readExam(){
        while (true) {
            try {
                System.out.println("Enter exam ID: ");
                int examID = scanner.nextInt();
                System.out.println("The exam with ID " + examID + " is " + read(examID).getExamName());
            } catch (NullPointerException E){
                System.out.println("Invalid exam ID. Please try again.");
            } catch(NoSuchElementException E){
                System.out.println("No such element");
            }
        }
    }

    public Examination read(int id){
        for (Examination exam : subject.getExamList()) {
            if (exam.getId() == id) {
                return exam;
            }
        }
        return null;
    }

    public void updateExam() {
        System.out.println("Enter exam ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter exam name: ");
        String name = scanner.nextLine();
        Examination exam1 = null;
        for (Examination exam : getSubject().getExamList()) {
            if (exam.getExamName().equals(name)) {
                update(id, exam);
                exam1 = exam;
            }
        }
        if (exam1 != null) {
            System.out.println("Exam " + exam1.getExamName() + " now has ID: " + id);
        } else {
            System.out.println("Exam not found");
        }
    }

    public void update(int usedId, Examination entity) {
        for (Examination exam : subject.getExamList()) {
            if (exam.getId() == usedId) {
                exam.setId(id);
                id++;
                entity.setId(usedId);
            }
        }
    }


    public void deleteExam() {
        System.out.println("Enter exam ID: ");
        int examID = scanner.nextInt();
        System.out.println("Exam " + read(examID).getExamName() + " deleted");
        delete(examID);
    }

    public void delete(int id){
        subject.getExamList().remove(read(id));
    }

    public void getStudentsInExam() {
        Examination exam = examSelecter();
        for (Map.Entry<Student, List<Integer>> entry : exam.getGrades().entrySet()){
            Student student = entry.getKey();
            System.out.println(student.getName());
        }
    }

    public void addGradeToStudent() {
        Examination exam = examSelecter();
        System.out.println("Enter student name: ");
        String studentName = scanner.nextLine();
        for (Student student : getSubject().getStudentList()) {
            if (student.getName().equals(studentName)) {
                System.out.println("Enter grade to put: ");
                int grade = scanner.nextInt();
                for (Map.Entry<Student,List<Integer>> entry : exam.getGrades().entrySet()){
                    Student student1 = entry.getKey();
                    if (student1.getName().equals(studentName)){
                        entry.getValue().add(grade);
                    }
                }
            }
        }

    }

    public void enroll(){
        Examination exam = examSelecter();
        Student student = null;
        while (true) {
            System.out.println("Enter student to enroll: ");
            String studentName = scanner.nextLine();
            for (Student student1 : getSubject().getStudentList()) {
                if (student1.getName().equals(studentName)) {
                    student = student1;
                    System.out.println("Student is now enrolled in "+ exam.getExamName());
                }
            }
            if (student == null){
                System.out.println("Student not in course");
            }
            else{
                break;
            }
        }
        exam.getGrades().put(student,new ArrayList<>());
    }

}
