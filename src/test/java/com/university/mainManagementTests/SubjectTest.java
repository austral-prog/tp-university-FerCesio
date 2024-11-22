package com.university.mainManagementTests;

import com.university.criteria.CriteriaCreator;
import com.university.examTypes.ExamCreator;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;
import com.university.mainManagement.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {
    private Subject subject;

    @BeforeEach
    void setup() {
        subject = new Subject("Mathematics");
        University.subjects.add(subject);
        // Agregar exámenes usando ExamCreator
        ExamCreator exam1 = new ExamCreator("Exam 1", "WRITTEN_EXAM");
        ExamCreator exam2 = new ExamCreator("Exam 2", "PRACTICAL_WORK");
        subject.getExamList().add(exam1.getExam());
        subject.getExamList().add(exam2.getExam());
    }

    @Test
    void testAddCriteria() {
        // Crear criterios usando CriteriaCreator
        List<String> examNames = Arrays.asList("Exam 1", "Exam 2");
        CriteriaCreator criteriaCreator = new CriteriaCreator("Mathematics", examNames, "AVERAGE_ABOVE_VALUE", 6.0f);
        criteriaCreator.criteriaAdder();

        // Verificar que los criterios se hayan agregado correctamente
        assertFalse(subject.getCriteriaList().isEmpty());
        assertEquals(1, subject.getCriteriaList().size());
    }

    @Test
    void testPassed() {
        // Crear criterios y estudiantes
        List<String> examNames = Arrays.asList("Exam 1", "Exam 2");
        CriteriaCreator criteriaCreator = new CriteriaCreator("Mathematics", examNames, "AVERAGE_ABOVE_VALUE", 6.0f);
        criteriaCreator.criteriaAdder();

        Student student = new Student("John Doe", "johndoe@email.com");
        University.students.add(student);
        subject.getStudentList().add(student);

        // Asignar calificaciones al estudiante
        subject.getExamList().get(0).distributeStudentGrades("John Doe", 7);
        subject.getExamList().get(1).distributeStudentGrades("John Doe", 5);

        // Verificar si el estudiante aprueba según los criterios
        assertTrue(subject.passed(student));
    }

    @Test
    void testCreateExamList() {
        ExamCreator exam3 = new ExamCreator("Final Exam", "FINAL_PRACTICAL_WORK");
        subject.createExamList(exam3.getExam(), "Final Exam");

        // Verificar que el examen se haya agregado correctamente
        assertTrue(subject.getExamList().contains(exam3.getExam()));
        assertTrue(subject.getExamMap().containsKey("Final Exam"));
        assertEquals(1, subject.getExamMap().get("Final Exam").size());
    }

    @Test
    void testExamComparator() {
        // Agregar exámenes desordenados al mapa
        ExamCreator exam1 = new ExamCreator("TP10", "WRITTEN_EXAM");
        ExamCreator exam2 = new ExamCreator("Examen Final", "FINAL_PRACTICAL_WORK");
        ExamCreator exam3 = new ExamCreator("TP1", "PRACTICAL_WORK");

        subject.getExamMap().put("TP1", List.of(exam3.getExam()));
        subject.getExamMap().put("Examen Final", List.of(exam2.getExam()));
        subject.getExamMap().put("TP10", List.of(exam1.getExam()));

        // Ordenar exámenes
        subject.ExamComparator();

        // Verificar que los exámenes estén ordenados correctamente
        assertEquals("Examen Final", subject.getExamList().get(0).getExamName());
        assertEquals("TP1", subject.getExamList().get(1).getExamName());
        assertEquals("TP10", subject.getExamList().get(2).getExamName());
    }
}
