package com.university.criteria;

import com.university.Examination;
import com.university.Student;
import com.university.Subject;

import java.util.List;

public interface Criteria {
    boolean evaluate(Subject subject, Student student);
    List<Examination> getExaminations();
    float getValue();

}
