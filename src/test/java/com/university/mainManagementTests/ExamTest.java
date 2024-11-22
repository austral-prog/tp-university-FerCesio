package com.university.mainManagementTests;

import com.university.examTypes.ExamCreator;
import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.University;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExamTest {

    @AfterEach
    void tearDown() {
        University.students.clear(); // Limpia todos los estudiantes después de cada test
    }

    // Subclase concreta para probar la clase abstracta
    static class MockExamination extends Examination {
        public MockExamination(String name, String type) {
            super(name, type);
        }

        @Override
        public float getFinalGrade(Student student) {
            List<Integer> grades = this.getGrades().get(student);
            return grades != null ? average(grades) : 0.0f;
        }


    }


    @Test
    void testDistributeStudentGrades() {
        // Crear la clase simulada y estudiantes
        ExamCreator examCreator = new ExamCreator("PRIMER PARCIAL", "WRITTEN_EXAM");
        Examination exam = examCreator.getExam();
        Student student1 = new Student("JohnDoe","john@gmail.com");
        Student student2 = new Student("JaneDoe","jane@gmail.com");

        // Añadir estudiantes a la lista de estudiantes de la universidad
        University.students.add(student1);
        University.students.add(student2);

        // Distribuir notas
        exam.distributeStudentGrades("JohnDoe", 90);
        exam.distributeStudentGrades("JaneDoe", 80);
        exam.distributeStudentGrades("JohnDoe", 85);

        // Verificar notas distribuidas
        TreeMap<Student, List<Integer>> grades = exam.getGrades();
        assertTrue(grades.containsKey(student1));
        assertTrue(grades.containsKey(student2));
        assertEquals(List.of(90, 85), grades.get(student1));
        assertEquals(List.of(80), grades.get(student2));
    }

    @Test
    void testGetFinalGrade() {
        // Crear la clase simulada y un estudiante
        ExamCreator examCreator = new ExamCreator("PRIMER PARCIAL", "WRITTEN_EXAM");
        Examination exam = examCreator.getExam();
        Student student = new Student("Alice","alice@gmail.com");

        // Añadir estudiante y distribuir notas
        University.students.add(student);
        exam.distributeStudentGrades("Alice", 70);
        exam.distributeStudentGrades("Alice", 80);
        exam.distributeStudentGrades("Alice", 90);

        // Verificar la nota final
        float finalGrade = exam.getFinalGrade(student);
        assertEquals(80.0f, finalGrade);
    }

    @Test
    void testSetAndGetId() {
        // Crear la clase simulada
        MockExamination exam = new MockExamination("Chemistry", "Quiz");

        // Establecer y verificar ID
        exam.setId(123);
        assertEquals(123, exam.getId());
    }

    @Test
    void testGetTypeAndName() {
        // Crear la clase simulada
        MockExamination exam = new MockExamination("History", "Essay");

        // Verificar nombre y tipo
        assertEquals("History", exam.getExamName());
        assertEquals("Essay", exam.getType());
    }
}
