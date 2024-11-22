package com.university.mainManagement;

import com.university.cli.Entity;

import java.util.*;

public class Student implements Entity {

    private final String name;
    private final String studentEmail;
    private final Set<Subject> subList = new TreeSet<>(Comparator.comparing(Subject::getName));
    private int id;


    public Student(String name, String studentEmail) {
        this.name = name;
        this.studentEmail = studentEmail;
    }

    public Student() {
        this.name = "";
        this.studentEmail = "";
    }

    public Set<Subject> getSubjects() {
        return this.subList;
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