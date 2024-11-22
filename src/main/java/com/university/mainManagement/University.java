package com.university.mainManagement;

import java.util.*;

public class University {
    public static Set<Subject> subjects = new TreeSet<>(Comparator.comparing(Subject::getName));
    public static Set<Student> students = new TreeSet<>(Comparator.comparing(Student::getName));


    public static void recieveData(Student student, Subject subject) {
        if (students.contains(student)) {
            for (Student st : students) {
                if (st.equals(student) && !st.getSubjects().contains(subject)) {
                    st.getSubjects().add(subject);
                    break;
                }
            }
        } else {
            University.students.add(student);
            student.getSubjects().add(subject);
        }
        University.subjects.add(subject);
    }

    public static void examDistribution(String subjectName, Examination exam1, String studentName, int grade){
        for (Subject subject1 : University.subjects) {           // Adds exams to the exam map {"Exam name": [ExamInstance]}
            if(subject1.getName().equals(subjectName)) {             // Then adds the list of instances to the Exam list in each subject
                subject1.createExamList(exam1, exam1.getExamName());
                for (Examination exam : subject1.getExamList()){
                    if (exam.getExamName().equals(exam1.getExamName()) && exam.getType().equals(exam1.getType())) {
                        exam.distributeStudentGrades(studentName,grade);
                    }
                }
            }
        }
    }

    public static void addStudentsToAllSubjects(){
        for (Subject subject : University.subjects) {
            for(Examination exam : subject.getExamList()) {
                for (Map.Entry<Student, List<Integer>> entry : exam.grades.entrySet()){
                    Student student = entry.getKey();
                    if (!subject.getStudentList().contains(student)) {
                        subject.getStudentList().add(student);
                    }
                }
            }
        }
    }
}


