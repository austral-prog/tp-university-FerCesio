package com.university.examTypes;

import com.university.Examination;
import com.university.Student;

import java.util.List;
import java.util.Map;

public class FinalPracticalWork extends Examination {
    public FinalPracticalWork(String type, String name) {
        super(type, name);
    }

    public float getFinalGrade(Student student){
        float finalGrade = 0;
        for(Map.Entry<Student, List<Integer>> entry : grades.entrySet()){
            double total = 0;
            Student s = entry.getKey();
            List<Integer> gradelist = entry.getValue();
            if (student.equals(s)){
                for(Integer grade : gradelist){
                    total += grade;
                }
            }
            finalGrade += total;
        }
        return finalGrade;
    }
}
