package com.university.examTypes;

import com.university.mainManagement.Examination;

public class ExamCreator {
    Examination exam;

    public ExamCreator(String evaluationName, String evaluationType){
        this.exam = switch (evaluationType) {
            case "WRITTEN_EXAM" -> new WrittenExam(evaluationName, evaluationType);
            case "ORAL_EXAM" -> new OralExam(evaluationName, evaluationType);
            case "PRACTICAL_WORK" -> new PracticalWork(evaluationName, evaluationType);
            case "FINAL_PRACTICAL_WORK" -> new FinalPracticalWork(evaluationName, evaluationType);
            default -> null;
        };

    }

    public Examination getExam(){
        return exam;
    }


}
