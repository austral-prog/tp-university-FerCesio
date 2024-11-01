package com.university;

import java.util.*;

public class Examination {

    String type;
    String name;
    TreeMap<Student, List<Integer>> grades = new TreeMap<>(new StudentComparator());


    public Examination(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getExamName() {
        return name;
    }


}

