package com.university.mainManagement;

import com.university.cli.Entity;

import java.util.*;

public abstract class Examination implements Entity {

    private String type;
    private String name;
    protected TreeMap<Student, List<Integer>> grades = new TreeMap<>(Comparator.comparing(Student::getName));
    private final List<Student> students = new ArrayList<>();
    int id;


    public Examination(String name, String type) {
        this.type = type;
        this.name = name;
    }

    public TreeMap<Student, List<Integer>> getGrades() {
        return this.grades;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void distributeStudentGrades(String studentName, int grade){
        for (Student student1 : University.students) {
            List<Integer> gradelist = new ArrayList<>();
            if (student1.getName().equals(studentName)) {
                if (!this.grades.containsKey(student1)) {
                    gradelist.add(grade);
                    this.grades.put(student1, gradelist);
                    this.getStudents().add(student1);
                }
                else {
                    this.grades.get(student1).add(grade);
                }
            }
        }
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getType() {
        return type;
    }

    public String getExamName() {
        return name;
    }

    public abstract float getFinalGrade(Student student);

    protected float average(List<Integer> list){
        int total = 0;
        float average = 0;
        for (int number : list){
            total = total + number;
        }
        average = (float) total / list.size();
        return average;
    }

}

