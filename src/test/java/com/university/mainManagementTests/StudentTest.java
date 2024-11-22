package com.university.mainManagementTests;

import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentTest {

    @Test
    void testConstructorWithParameters() {
        // Crear un estudiante usando el constructor con parámetros
        Student student = new Student("Alice", "alice@example.com");

        // Verificar los valores iniciales
        assertEquals("Alice", student.getName());
        assertEquals("alice@example.com", student.getStudentEmail());
        assertTrue(student.getSubjects().isEmpty());
    }

    @Test
    void testDefaultConstructor() {
        // Crear un estudiante usando el constructor por defecto
        Student student = new Student();

        // Verificar valores iniciales
        assertEquals("", student.getName());
        assertEquals("", student.getStudentEmail());
        assertTrue(student.getSubjects().isEmpty());
    }

    @Test
    void testSetAndGetId() {
        // Crear un estudiante y establecer el ID
        Student student = new Student("Bob", "bob@example.com");
        student.setId(101);

        // Verificar el ID
        assertEquals(101, student.getId());
    }

    @Test
    void testAddSubjects() {
        // Crear un estudiante y asignar materias
        Student student = new Student("Charlie", "charlie@example.com");
        Subject math = new Subject("Math");
        Subject physics = new Subject("Physics");

        // Añadir materias al estudiante
        student.getSubjects().add(math);
        student.getSubjects().add(physics);

        // Verificar las materias asignadas
        Set<Subject> subjects = student.getSubjects();
        assertEquals(2, subjects.size());
        assertTrue(subjects.contains(math));
        assertTrue(subjects.contains(physics));
    }

    @Test
    void testSubjectsSortedByName() {
        // Crear un estudiante y asignar materias desordenadas
        Student student = new Student("Daisy", "daisy@example.com");
        Subject physics = new Subject("Physics");
        Subject math = new Subject("Math");

        // Añadir materias
        student.getSubjects().add(physics);
        student.getSubjects().add(math);

        // Verificar orden en la lista
        Subject[] subjects = student.getSubjects().toArray(new Subject[0]);
        assertEquals("Math", subjects[0].getName());
        assertEquals("Physics", subjects[1].getName());
    }
}

