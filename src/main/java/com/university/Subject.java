package com.university;

import java.util.*;

public class Subject {

    String name;
    String teacher;
    Set<Examination> examList = new HashSet<>();
    Map<String, Examination> examMap = new HashMap<>();


    public Subject(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subject subject = (Subject) obj;
        return Objects.equals(name, subject.name);
    }

}
