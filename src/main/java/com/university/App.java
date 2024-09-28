package com.university;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args){
        String archivoEntrada = "src/main/resources/input.csv";  // Archivo CSV de entrada
        String archivoSalida = "src/main/resources/solution.csv";  // Archivo CSV de salida

        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {

            String linea;
            // Leer el archivo CSV línea por línea

            String lineaPrincipal = String.join(",","Student_Name","Course_Count");
            bw.write(lineaPrincipal);
            bw.newLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");


                String studentName;
                String studentEmail;
                String teacherName;
                String subjectName;

                subjectName = datos[1];
                studentName = datos[2];
                studentEmail = datos[3];
                teacherName = datos[4];
                Student student = new Student(studentName, studentEmail);
                Subject subject = new Subject(subjectName, teacherName);


                if (University.students.contains(student)) {
                    for(Student st : University.students){
                        if(st.equals(student) && !st.subList.contains(subject)){
                            st.subList.add(subject);
                            break;
                        }
                    }
                }
                else {
                    if(!student.getName().equals("Student_Name")){
                        University.students.add(student);
                        student.subList.add(subject);
                    }
                }
                if (!University.subjects.contains(subject)) {
                    University.subjects.add(subject);
                }

            }
            for (Student student1 : University.students) {
                String lineas = String.join(",", student1.getName(), String.valueOf(student1.subList.size()));
                bw.write(lineas);
                bw.newLine();
            }
        }
    catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
