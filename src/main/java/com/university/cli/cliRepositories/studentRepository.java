package com.university.cli.cliRepositories;


import com.university.mainManagement.Student;
import com.university.mainManagement.University;

import java.util.*;

import static com.university.cli.MainCLI.scanner;

public class studentRepository implements CRUDRepository<Student> {
    int id = 0;
    Class<Student> entityClass;

    public void idSetter(Set<Student> set){
        for (Student entity : set) {
            entity.setId(id);
            id++;
        }
    }

    public studentRepository(Class<Student> entityClass) {
        this.entityClass = entityClass;
    }

    public String getIdentifier(){
        return "Student";
    }

    public Class<Student> getEntityClass(){
        return entityClass;
    }

    public void createStudent(){
        System.out.println("Enter student name");
        String name = scanner.nextLine();

        System.out.println("Enter student email");
        String email = scanner.nextLine();

        Student student = new Student(name, email);
        student.setId(id);
        id++;
        University.students.add(student);
        System.out.println("Student created");
    }

    public void create(Student student){
        student.setId(id);
        id++;
        University.students.add(student);
    }

    public void readStudent(){
        while (true) {
            try {
                System.out.println("Enter student ID");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("The student with ID " + id + " is " + read(id).getName());
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Invalid student ID");
            } catch (NullPointerException e) {
                System.out.println("Student not found");
            } catch (NumberFormatException e) {
                System.out.println("Not a number");
            }
        }
    }

    public Student read(int id){
        for (Student student : University.students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent() {
        System.out.println("Enter student ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter student name");
        String name = scanner.nextLine();
        Student student1 = null;
        for (Student student : University.students){
            if (student.getName().equals(name)){
                update(id, student);
                student1 = student;
            }
        }
        System.out.println("Student " + student1.getName() + " now has ID: " + id);
    }

    public void update(int usedId, Student entity){
        for (Student student : University.students) {
            if (student.getId() == usedId) {
                student.setId(id);
                id++;
                entity.setId(usedId);
            }
        }
    }

    public void deleteStudent(){
        System.out.println("Enter student ID");
        int id = scanner.nextInt();
        System.out.println("Student " + read(id).getName() + " deleted");
        delete(id);
    }

    public void delete(int id){
        University.students.remove(read(id));
    }

    public void listAllStudents(){
        List<String> studentNameList = new ArrayList<>();
        for (Student student : University.students){
            studentNameList.add(student.getName());
        }
        System.out.println(studentNameList);
    }

}
