package com.university.repositoriesTests;

import com.university.cli.cliRepositories.examRepository;
import com.university.examTypes.FinalPracticalWork;
import com.university.examTypes.OralExam;
import com.university.examTypes.WrittenExam;
import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExamRepositoryTest {
    private examRepository repository;
    private Subject subject;


    @BeforeEach
    void setup() {
        subject = new Subject("Mathematics");
        repository = new examRepository(Examination.class, subject);
    }

    @Test
    void testCreateExam() {
        Examination exam = new WrittenExam("Final Exam", "WRITTEN_EXAM");
        repository.create(exam);

        // Verificar que el examen se agregó correctamente
        assertEquals(1, subject.getExamList().size());
        assertEquals("Final Exam", subject.getExamList().get(0).getExamName());
    }

    @Test
    void testReadExam() {
        Examination exam = new OralExam("Midterm", "ORAL_EXAM");
        repository.create(exam);

        // Leer el examen por ID
        Examination retrievedExam = repository.read(0);
        assertNotNull(retrievedExam);
        assertEquals("Midterm", retrievedExam.getExamName());
    }


    @Test
    void testDeleteExam() {
        Examination exam = new FinalPracticalWork("Final Project", "FINAL_PRACTICAL_WORK");
        repository.create(exam);

        // Verificar que el examen se agregó
        assertEquals(1, subject.getExamList().size());

        // Eliminar el examen
        repository.delete(0);

        // Verificar que el examen fue eliminado
        assertNull(repository.read(0));
        assertEquals(0, subject.getExamList().size());
    }

    @Test
    void testAddGradeToStudent() {
        Student student = new Student("Alice", "alice@example.com");
        subject.getStudentList().add(student);

        Examination exam = new WrittenExam("Exam 1", "WRITTEN_EXAM");
        repository.create(exam);

        // Agregar estudiante al examen
        exam.getGrades().put(student, new ArrayList<>());

        // Agregar calificación
        exam.getGrades().get(student).add(85);

        // Verificar calificación
        assertEquals(1, exam.getGrades().get(student).size());
        assertEquals(85, exam.getGrades().get(student).get(0));
    }

}

