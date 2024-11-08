package com.university.criteria;

import com.university.Examination;
import com.university.Student;
import com.university.Subject;

import java.util.ArrayList;
import java.util.List;

public class MinAboveValue implements Criteria {
    String name;
    float value;
    List<Examination> examinations = new ArrayList<>();

    public MinAboveValue(String name, float value, List<Examination> examinations) {
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
        float min = 9999;
        for (Examination exam : examinations) {
            if (exam.getFinalGrade(student) < min) {
                min = exam.getFinalGrade(student);
            }
        }
        if (min > value) {
            return true;
        }
        return false;
    }

}
