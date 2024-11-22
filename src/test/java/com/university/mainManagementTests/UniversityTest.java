package com.university.mainManagementTests;

import com.university.examTypes.ExamCreator;
import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;
import com.university.mainManagement.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


class UniversityTest {

    @BeforeEach
    void setup() {
        University.students = new HashSet<>();
        University.subjects = new HashSet<>();
    }

    @Test
    void testRecieveData() {
        Student student = new Student("John Doe", "john.doe@example.com");
        Subject subject = new Subject("Mathematics");

        // Agregar estudiante y materia
        University.recieveData(student, subject);

        // Verificar que el estudiante y la materia se hayan agregado
        assertTrue(University.students.contains(student));
        assertTrue(University.subjects.contains(subject));

        // Verificar que la materia esté asociada al estudiante
        assertTrue(student.getSubjects().contains(subject));
    }

    @Test
    void testRecieveDataExistingStudent() {
        Student student = new Student("Jane Doe", "jane.doe@example.com");
        Subject subject1 = new Subject("Physics");
        Subject subject2 = new Subject("Chemistry");

        // Agregar estudiante con una materia
        University.recieveData(student, subject1);

        // Agregar otra materia al mismo estudiante
        University.recieveData(student, subject2);

        // Verificar que el estudiante solo esté una vez y tenga ambas materias
        assertEquals(1, University.students.size());
        assertTrue(student.getSubjects().contains(subject1));
        assertTrue(student.getSubjects().contains(subject2));
    }

    @Test
    void testExamDistribution() {
        // Crear estudiante, materia y examen
        Student student = new Student("Alice", "alice@example.com");
        Subject subject = new Subject("Biology");
        ExamCreator examCreator = new ExamCreator("Final Exam", "WRITTEN_EXAM");

        University.recieveData(student, subject);

        // Distribuir examen y calificación
        University.examDistribution("Biology", examCreator.getExam(), "Alice", 85);

        // Verificar que el examen y la calificación estén correctamente distribuidos
        assertFalse(subject.getExamList().isEmpty());
        Examination distributedExam = subject.getExamList().get(0);
        assertEquals(85, distributedExam.getGrades().get(student).get(0));
    }

    @Test
    void testAddStudentsToAllSubjects() {
        // Crear dos estudiantes, materia y exámenes
        Student student1 = new Student("Bob", "bob@example.com");
        Student student2 = new Student("Carol", "carol@example.com");
        Subject subject = new Subject("History");
        ExamCreator exam1 = new ExamCreator("Exam 1", "ORAL_EXAM");
        ExamCreator exam2 = new ExamCreator("Exam 2", "PRACTICAL_WORK");

        University.recieveData(student1, subject);
        University.recieveData(student2, subject);

        // Distribuir calificaciones
        University.examDistribution("History", exam1.getExam(), "Bob", 90);
        University.examDistribution("History", exam2.getExam(), "Carol", 85);

        // Llamar al método para agregar estudiantes a todas las materias
        University.addStudentsToAllSubjects();

        // Verificar que ambos estudiantes estén registrados en la lista de estudiantes de la materia
        assertTrue(subject.getStudentList().contains(student1));
        assertTrue(subject.getStudentList().contains(student2));
    }
}

