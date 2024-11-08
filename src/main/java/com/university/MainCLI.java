package com.university;

import java.util.Scanner;

public class MainCLI {
    private static final CRUDRepository<Student> studentRepository = new studentRepository<>();
    private static final CRUDRepository<Examination> examRepository = new examRepository<>();
    private static final CRUDRepository<Subject> courseRepository = new courseRepository<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- University Management CLI ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (choice) {
                case 1 -> manageStudents();
                case 2 -> manageCourses();
                case 3 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }



    private static void manageStudents() {
        System.out.println("1. Create Student");
        System.out.println("2. Read Student by ID");
        System.out.println("3. List All Students");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        // Implementa de manera similar a los ejemplos anteriores
    }

    private static void manageCourses() {
        System.out.println("1. Create Course");
        System.out.println("2. Read Course by ID");
        System.out.println("3. List All Courses");
        System.out.println("4. Update Course");
        System.out.println("5. Manage Exams");
        System.out.println("6. Delete Course");
        // Implementa de manera similar a los ejemplos anteriores

    }

    private static void manageExams() {
        System.out.println("1. Create Exam");
        System.out.println("2. Read Exam by ID");
        System.out.println("3. List All Exams");
        System.out.println("4. Update Exam");
        System.out.println("5. Delete Exam");
    }

}
