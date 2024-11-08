package com.university;

import java.util.*;

public abstract class Examination implements Entity {

    String type;
    String name;
    protected TreeMap<Student, List<Integer>> grades = new TreeMap<>(Comparator.comparing(Student::getName));
    List<Student> students = new ArrayList<>();
    int id;


    public Examination(String name, String type) {
        this.type = type;
        this.name = name;
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

