package com.university.criteria;

import com.university.mainManagement.Examination;
import com.university.mainManagement.Subject;
import com.university.mainManagement.University;

import java.util.ArrayList;
import java.util.List;

public class CriteriaCreator {

    private final List<String> stringExamList;
    private final List<Examination> examCriteriaList = new ArrayList<>();
    private final String subjectName;
    private final String criteriaType;
    private final float criteriaValue;

    public CriteriaCreator(String subjectName, List<String> stringExamList, String criteriaType, float criteriaValue) {
        this.stringExamList = stringExamList;
        this.subjectName = subjectName;
        this.criteriaType = criteriaType;
        this.criteriaValue = criteriaValue;
        for (Subject subject1 : University.subjects) {
            if (subject1.getName().equals(subjectName)) {
                for (String e : stringExamList) {
                    for (Examination exam : subject1.getExamList()) {
                        if (exam.getExamName().equals(e)) {
                            examCriteriaList.add(exam);
                        }
                    }
                }
            }
        }
    }

    public void criteriaAdder(){
        for (Subject subject:University.subjects){
            if(subject.getName().equals(subjectName)){
                if (criteriaType.equals("MAX_ABOVE_VALUE")) {
                    subject.addCriteria(new MaxAboveValue(criteriaType,criteriaValue,examCriteriaList));
                }
                else if (criteriaType.equals("MIN_ABOVE_VALUE")) {
                    subject.addCriteria(new MinAboveValue(criteriaType,criteriaValue,examCriteriaList));
                }
                else if (criteriaType.equals("AVERAGE_ABOVE_VALUE")) {
                    subject.addCriteria(new AverageAboveValue(criteriaType,criteriaValue,examCriteriaList));
                }
            }
        }
    }
}
