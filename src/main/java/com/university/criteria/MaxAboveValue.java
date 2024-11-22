package com.university.criteria;

import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;

import java.util.ArrayList;
import java.util.List;

public class MaxAboveValue implements Criteria {

    String name;
    float value;
    List<Examination> examinations = new ArrayList<>();

    public MaxAboveValue(String name, float value, List<Examination> examinations) {
        this.name = name;
        this.value = value;
        this.examinations = examinations;
    }

    public float getValue() {
        return value;
    }

    public List<Examination> getExaminations(){
        return examinations;
    }

    public boolean evaluate(Subject subject, Student student) {
        if (value>examinations.getFirst().getFinalGrade(student)){
            return true;
        }
        return false;
    }
}
