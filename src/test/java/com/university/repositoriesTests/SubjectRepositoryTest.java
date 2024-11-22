package com.university.repositoriesTests;

import com.university.cli.cliRepositories.subjectRepository;
import com.university.mainManagement.Subject;
import com.university.mainManagement.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SubjectRepositoryTest {

    private subjectRepository repository;
    private Set<Subject> subjects;

    @BeforeEach
    void setUp() {
        subjects = new HashSet<>();
        University.subjects = subjects; // Simulamos el conjunto de asignaturas en la universidad
        repository = new subjectRepository(Subject.class);
    }

    @Test
    void testCreate() {
        Subject subject = new Subject("Math");
        repository.create(subject);

        assertTrue(University.subjects.contains(subject));
        assertEquals(0, subject.getId()); // El primer ID asignado debe ser 0
    }

    @Test
    void testRead() {
        Subject subject = new Subject("Physics");
        repository.create(subject);

        Subject retrieved = repository.read(0); // Buscar por ID
        assertNotNull(retrieved);
        assertEquals("Physics", retrieved.getName());
    }

    @Test
    void testUpdate() {
        Subject subject = new Subject("Biology");
        repository.create(subject);

        Subject updatedSubject = new Subject("Updated Biology");
        repository.update(0, updatedSubject);

        assertEquals(0, updatedSubject.getId());
    }

    @Test
    void testDelete() {
        Subject subject = new Subject("Chemistry");
        repository.create(subject);

        repository.delete(0);

        assertNull(repository.read(0));
        assertFalse(University.subjects.contains(subject));
    }

    @Test
    void testListAllSubjects() {
        Subject math = new Subject("Math");
        Subject physics = new Subject("Physics");
        repository.create(math);
        repository.create(physics);

        String[] subjectNames = {"Math", "Physics"};
        int index = 0;
        for (Subject subject : University.subjects) {
            assertEquals(subjectNames[index], subject.getName());
            index++;
        }
    }

    @Test
    void testIdSetter() {
        Set<Subject> newSubjects = new HashSet<>();
        newSubjects.add(new Subject("Art"));
        newSubjects.add(new Subject("Music"));

        repository.idSetter(newSubjects);

        int id = 0;
        for (Subject subject : newSubjects) {
            assertEquals(id, subject.getId());
            id++;
        }
    }
}
