package com.university.criteria;

import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;

import java.util.List;

public interface Criteria {
    boolean evaluate(Subject subject, Student student);
    List<Examination> getExaminations();
    float getValue();

}
