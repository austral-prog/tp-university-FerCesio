package com.university.examTypes;

import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;

import java.util.List;
import java.util.Map;

public class OralExam extends Examination {

    public OralExam(String type, String name) {
        super(type, name);
    }

    public float getFinalGrade(Student student) {
        float finalGrade = 0;
        for(Map.Entry<Student, List<Integer>> entry : grades.entrySet()){
            List<Integer> gradelist = entry.getValue();
            Student s = entry.getKey();
            if (student.equals(s)) {
                finalGrade = gradelist.get(0);
            }
        }
        return finalGrade;
    }
}
