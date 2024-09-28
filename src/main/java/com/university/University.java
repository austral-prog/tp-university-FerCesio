package com.university;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class University {
    static Set<Subject> subjects = new HashSet<>();
    static Set<Student> students = new TreeSet<>(Comparator.comparing(Student::getName));

}
