package com.university.repositoriesTests;

import com.university.cli.cliRepositories.studentRepository;
import com.university.mainManagement.Student;
import com.university.mainManagement.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private studentRepository repository;
    private Set<Student> students;

    @BeforeEach
    void setUp() {
        students = new HashSet<>();
        University.students = students; // Simulamos el conjunto global de estudiantes
        repository = new studentRepository(Student.class);
    }

    @Test
    void testCreate() {
        Student student = new Student("Alice", "alice@example.com");
        repository.create(student);

        assertTrue(University.students.contains(student));
        assertEquals(0, student.getId()); // El primer ID asignado debe ser 0
    }

    @Test
    void testRead() {
        Student student = new Student("Bob", "bob@example.com");
        repository.create(student);

        Student retrieved = repository.read(0); // Buscar por ID
        assertNotNull(retrieved);
        assertEquals("Bob", retrieved.getName());
    }

    @Test
    void testUpdate() {
        Student student = new Student("Charlie", "charlie@example.com");
        repository.create(student);

        Student updatedStudent = new Student("Updated Charlie", "updated@example.com");
        repository.update(0, updatedStudent);

        assertEquals(0, updatedStudent.getId());
    }

    @Test
    void testDelete() {
        Student student = new Student("Dave", "dave@example.com");
        repository.create(student);

        repository.delete(0);

        assertNull(repository.read(0));
        assertFalse(University.students.contains(student));
    }

    @Test
    void testListAllStudents() {
        Student alice = new Student("Alice", "alice@example.com");
        Student bob = new Student("Bob", "bob@example.com");
        repository.create(alice);
        repository.create(bob);

        List<String> studentNames = new ArrayList<>();
        for (Student student : University.students) {
            studentNames.add(student.getName());
        }

        assertTrue(studentNames.contains("Alice"));
        assertTrue(studentNames.contains("Bob"));
    }

    @Test
    void testIdSetter() {
        Set<Student> newStudents = new HashSet<>();
        newStudents.add(new Student("Eve", "eve@example.com"));
        newStudents.add(new Student("Frank", "frank@example.com"));

        repository.idSetter(newStudents);

        int id = 0;
        for (Student student : newStudents) {
            assertEquals(id, student.getId());
            id++;
        }
    }

}

