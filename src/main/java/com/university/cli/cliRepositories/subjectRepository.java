package com.university.cli.cliRepositories;

import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;
import com.university.mainManagement.University;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.university.cli.MainCLI.scanner;

public class subjectRepository implements CRUDRepository<Subject> {
    int id = 0;
    Class<Subject> entityClass;

    public subjectRepository(Class<Subject> entityClass) {
        this.entityClass = entityClass;
    }


    public String getIdentifier(){
        return "Subject";
    }

    public Class<Subject> getEntityClass(){
        return entityClass;
    }

    public void idSetter(Set<Subject> set){
        for (Subject entity : set) {
            entity.setId(id);
            id++;
        }
    }

    public void createCourse(){
        System.out.println("Enter course name");
        String name = scanner.nextLine();
        create(new Subject(name));
        System.out.println("Subject "+ name +" created");
    }

    public void create(Subject subject){
        subject.setId(id);
        id++;
        University.subjects.add(subject);
    }

    public void readCourse(){
        try {
            System.out.println("Enter course ID");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("The course with ID " + id + " is " + read(id).getName());
        } catch (NoSuchElementException e){
            System.out.println("Invalid course ID");
            scanner.nextLine();
        } catch (NullPointerException e){
            System.out.println("Course not found");
            scanner.nextLine();
        } catch (NumberFormatException e){
            System.out.println("Not a number");
        }
    }

    public Subject read(int id){
        for (Subject subject : University.subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null;
    }

    public void updateCourse(){
        System.out.println("Enter course ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter course name");
        String name = scanner.nextLine();
        Subject subject1 = null;
        for (Subject subject : University.subjects) {
            if (subject.getName().equals(name)) {
                update(id, subject);
                subject1 = subject;
            }
        }
        System.out.println("Subject " + subject1.getName() + " now has ID: " + id);
    }

    public void update(int usedId, Subject entity){
        for (Subject subject : University.subjects) {
            if (subject.getId() == usedId) {
                subject.setId(id);
                id++;
                entity.setId(usedId);
            }
        }
    }

    public void deleteCourse(){
        System.out.println("Enter course ID");
        int id = scanner.nextInt();
        System.out.println("Course " + read(id).getName() + " deleted");
        delete(id);
    }

    public void delete(int id){
        University.subjects.remove(read(id));
    }

    public void listAllSubjects(){
        List<String> subjectNameList = new ArrayList<>();
        for (Subject subject : University.subjects){
            subjectNameList.add(subject.getName());
        }
        System.out.println(subjectNameList);
    }

    public void getPassingStudents(){
        Subject subject1 = courseSelecter();
        List<Student> students = new ArrayList<>();
        for (Student student : subject1.getStudentList()){
            if (subject1.passed(student)){
                students.add(student);
                System.out.println(student.getName());
            }
        }
        if (students.isEmpty()){
            System.out.println("None of the students passed");
        }
    }

    public Subject courseSelecter() {
        return getSubject();
    }

    public static Subject getSubject() {
        while (true) {
            System.out.println("Enter course name:");
            String name = scanner.nextLine();
            Subject selectedSubject = null;

            for (Subject subject : University.subjects) {
                if (subject.getName().equals(name)) {
                    selectedSubject = subject;
                    System.out.println("The course was found");
                    break;
                }
            }
            if (!(selectedSubject == null)) {
                return selectedSubject;
            }
            else {
                System.out.println("Course not found");
            }
        }
    }

    public void listAllExams(){
        List<String> examNameList = new ArrayList<>();
        Subject subject1 = courseSelecter();
        for (Examination examination : subject1.getExamList()){
            examNameList.add(examination.getExamName());
        }
        System.out.println(examNameList);
    }

    public void enroll(){
        Subject subject1 = courseSelecter();
        Student student = null;
        while ( true) {
            System.out.println("Enter student to enroll:");
            String name = scanner.nextLine();

            for (Student student1 : University.students) {
                if (student1.getName().equals(name)) {
                    student = student1;
                    System.out.println("Student "+ name+ " is now enrolled in "+ subject1.getName());
                    break;
                }
            }
            if (student == null) {
                System.out.println("Student not found");
            }
            else{
                break;
            }
        }
        subject1.getStudentList().add(student);
    }
}
