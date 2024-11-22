package com.university.cli;


import com.university.App;
import com.university.cli.cliRepositories.CRUDRepository;
import com.university.cli.cliRepositories.examRepository;
import com.university.cli.cliRepositories.studentRepository;
import com.university.cli.cliRepositories.subjectRepository;
import com.university.mainManagement.Examination;
import com.university.mainManagement.Student;
import com.university.mainManagement.Subject;
import com.university.mainManagement.University;


import java.util.Scanner;

import static com.university.cli.cliRepositories.subjectRepository.getSubject;

public class MainCLI implements CLI {
    public studentRepository studentRepo = new studentRepository(Student.class);
    public subjectRepository courseRepo = new subjectRepository(Subject.class);
    public static final Scanner scanner = new Scanner(System.in);
    public CRUDRepository<?>[] crudInterfaces = new CRUDRepository[] {studentRepo, courseRepo};

    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        App.main(new String[0]);
        studentRepo.idSetter(University.students);
        courseRepo.idSetter(University.subjects);
        universityManagement();
    }

    public static void main(String[] args) {
        MainCLI cli = new MainCLI();
        cli.runCLI(cli.crudInterfaces);
    }

    public void universityManagement(){
        boolean running = true;
        while (running) {
            System.out.println("\n--- University Management CLI ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Exit");

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



    public void manageStudents() {
        boolean managingStudents = true;
        while (managingStudents) {
            System.out.println("");
            System.out.println("--- Manage Students ---");
            System.out.println("1. Create Student");
            System.out.println("2. Read Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. List All Students");
            System.out.println("5. Delete Student");
            System.out.println("6. Back to university management");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> studentRepo.createStudent();
                case 2 -> studentRepo.readStudent();
                case 3 -> studentRepo.updateStudent();
                case 4 -> studentRepo.listAllStudents();
                case 5 -> studentRepo.deleteStudent();
                case 6 -> managingStudents = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

    }


     public void manageCourses() {
        boolean managingCourses = true;
        while (managingCourses) {
            System.out.println("");
            System.out.println("--- Manage Courses ---");
            System.out.println("1. Create course");
            System.out.println("2. Read course by ID");
            System.out.println("3. Update course");
            System.out.println("4. Delete course");
            System.out.println("5. Manage exams");
            System.out.println("6. List all courses");
            System.out.println("7. Get passing students in course");
            System.out.println("8. List exams");
            System.out.println("9. Enroll student in course");
            System.out.println("10. Back to university management");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> courseRepo.createCourse();
                case 2 -> courseRepo.readCourse();
                case 3 -> courseRepo.updateCourse();
                case 4 -> courseRepo.deleteCourse();
                case 5 -> manageExams();
                case 6 -> courseRepo.listAllSubjects();
                case 7 -> courseRepo.getPassingStudents();
                case 8 -> courseRepo.listAllExams();
                case 9 -> courseRepo.enroll();
                case 10 -> managingCourses = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

    }

    public Subject courseSelecter() {
        return getSubject();
    }

    public void manageExams() {
        Subject selectedCourse = courseSelecter();
        examRepository examRepo = new examRepository(Examination.class, selectedCourse);
        boolean managingExams = true;
        while (managingExams) {
            System.out.println("");
            System.out.println("--- Manage Exams ---");
            System.out.println("1. Create exam");
            System.out.println("2. Read exam");
            System.out.println("3. Update exam");
            System.out.println("4. Delete exam");
            System.out.println("5. Get students in exam");
            System.out.println("6. Add grade to student in exam");
            System.out.println("7. Enroll student in exam");
            System.out.println("8. Back to manage courses");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> examRepo.createExam();
                case 2 -> examRepo.readExam();
                case 3 -> examRepo.updateExam();
                case 4 -> examRepo.deleteExam();
                case 5 -> examRepo.getStudentsInExam();
                case 6 -> examRepo.addGradeToStudent();
                case 7 -> examRepo.enroll();
                case 8 -> managingExams = false;
                default -> System.out.println("Invalid option, try again");
            }
        }
    }

}
