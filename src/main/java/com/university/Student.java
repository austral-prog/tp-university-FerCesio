package com.university;

import java.util.*;

public class Student implements Entity {

    String name;
    String studentEmail;
    Set<Subject> subList = new TreeSet<>(Comparator.comparing(Subject::getName));
    int id;


    public Student(String name, String studentEmail) {
        this.name = name;
        this.studentEmail = studentEmail;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(name, student.name);
    }

}