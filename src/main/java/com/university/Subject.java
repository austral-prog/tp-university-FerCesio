package com.university;

import com.university.criteria.Criteria;

import java.util.*;

public class Subject implements Entity {

    String name;
    String teacher;
    List<Examination> examList = new ArrayList<>();
    Map<String, List<Examination>> examMap = new HashMap<>(); // {"Examen", Examen (Objeto)}
    List<Criteria> criteriaList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    int id;


    public Subject(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addCriteria(Criteria criteria) {
        criteriaList.add(criteria);
    }


    public boolean passed(Student student) {
        for (Criteria c : criteriaList) {
            if (!c.evaluate(this,student)){
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subject subject = (Subject) obj;
        return Objects.equals(name, subject.name);
    }

    public void ExamComparator(){
        List<Examination> examList2 = new ArrayList<>();
        while (!examMap.isEmpty()){
            if (examMap.containsKey("Examen Final")) {
                examList2.addAll(examMap.get("Examen Final"));
                examMap.remove("Examen Final");

            }
            else if (examMap.containsKey("Primer Parcial")) {
                examList2.addAll(examMap.get("Primer Parcial"));
                examMap.remove("Primer Parcial");
            }
            else if (examMap.containsKey("Segundo Parcial")) {
                examList2.addAll(examMap.get("Segundo Parcial"));
                examMap.remove("Segundo Parcial");
            }
            else if (examMap.containsKey("TP Final")) {
                examList2.addAll(examMap.get("TP Final"));
                examMap.remove("TP Final");
            }
            else if (examMap.containsKey("TP1")){
                examList2.addAll(examMap.get("TP1"));
                examMap.remove("TP1");
            }
            else if (examMap.containsKey("TP10")){
                examList2.addAll(examMap.get("TP10"));
                examMap.remove("TP10");
            }
            else if (examMap.containsKey("TP2")){
                examList2.addAll(examMap.get("TP2"));
                examMap.remove("TP2");
            }
            else if (examMap.containsKey("TP3")){
                examList2.addAll(examMap.get("TP3"));
                examMap.remove("TP3");
            }
            else if (examMap.containsKey("TP4")){
                examList2.addAll(examMap.get("TP4"));
                examMap.remove("TP4");
            }
            else if (examMap.containsKey("TP5")){
                examList2.addAll(examMap.get("TP5"));
                examMap.remove("TP5");
            }
            else if (examMap.containsKey("TP6")){
                examList2.addAll(examMap.get("TP6"));
                examMap.remove("TP6");
            }
            else if (examMap.containsKey("TP7")){
                examList2.addAll(examMap.get("TP7"));
                examMap.remove("TP7");
            }
            else if (examMap.containsKey("TP8")){
                examList2.addAll(examMap.get("TP8"));
                examMap.remove("TP8");
            }
            else if (examMap.containsKey("TP9")) {
                examList2.addAll(examMap.get("TP9"));
                examMap.remove("TP9");
            }
            else {
                examMap.clear();
            }

        }
        examList = examList2;
    }
}
