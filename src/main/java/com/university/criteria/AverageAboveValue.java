package com.university.criteria;

import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;

import java.util.ArrayList;
import java.util.List;

public class AverageAboveValue implements Criteria {

    String name;
    float value;
    List<Examination> examinations = new ArrayList<>();

    public AverageAboveValue(String name, float value, List<Examination> examinations) {
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

    public boolean evaluate(Subject subject, Student student){
        float total = 0;
        for (Examination exam : examinations){
            total += exam.getFinalGrade(student);
        }
        total = total / examinations.size();
        if (total > value){
            return true;
        }
        return false;
    }
}
