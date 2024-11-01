package com.university;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class University {
    static Set<Subject> subjects = new TreeSet<>(Comparator.comparing(Subject::getName));
    static Set<Student> students = new TreeSet<>(Comparator.comparing(Student::getName));


    public static void recieveData(Student student, Subject subject) {
        if (students.contains(student)) {
            for (Student st : students) {
                if (st.equals(student) && !st.subList.contains(subject)) {
                    st.subList.add(subject);
                    break;
                }
            }
        } else {
            if (!student.getName().equals("Student_Name")) {
                University.students.add(student);
                student.subList.add(subject);
            }
        }
        if (!University.subjects.contains(subject)) {
            University.subjects.add(subject);
        }
    }
}


