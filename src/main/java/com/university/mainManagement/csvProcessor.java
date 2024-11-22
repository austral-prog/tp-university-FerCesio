package com.university.mainManagement;

import com.university.criteria.CriteriaCreator;
import com.university.examTypes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class csvProcessor {

    String fileToRead;
    String fileToWrite;

    public csvProcessor(String fileToRead, String fileToWrite) {
        this.fileToRead = fileToRead;
        this.fileToWrite = fileToWrite;
    }


    public void expected1(){
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))) {

            String linea;

            String lineaPrincipal = String.join(",","Student_Name","Course_Count");
            bw.write(lineaPrincipal);
            bw.newLine();
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String studentName;
                String studentEmail;
                String teacherName;
                String subjectName;

                subjectName = datos[1];
                studentName = datos[2];
                studentEmail = datos[3];
                Student student = new Student(studentName, studentEmail);
                Subject subject = new Subject(subjectName);

                University.recieveData(student,subject);

            }
            for (Student student1 : University.students) {
                String lineas = String.join(",", student1.getName(), String.valueOf(student1.getSubjects().size()));
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

    public void expected2(){
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))) {

            String line;

            String header = String.join(",","Subject_Name","Evaluation_Name","Student_Name","Grade");
            bw.write(header);
            bw.newLine();
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                String studentName = info[0];
                String subject = info[1];
                String evaluationType = info[2];
                String evaluationName = info[3];
                String exerciseName = info[4];
                int grade = Integer.parseInt(info[5]);
                Examination exam1;

                ExamCreator examCreator = new ExamCreator(evaluationName,evaluationType);
                exam1 = examCreator.getExam();

                University.examDistribution(subject,exam1,studentName,grade); // Toma el nombre de la materia, una instancia
            }

            for (Subject subject : University.subjects) {
                subject.ExamComparator();
                List<Examination> exams = subject.getExamList();

                for (int i = 0; i < exams.size(); i++) {
                    Examination exam = exams.get(i);

                    // Verificamos si el examen actual tiene el mismo nombre que el siguiente
                    if (i < exams.size() - 1 && exam.getExamName().equals(exams.get(i + 1).getExamName())) {
                        Examination nextExam = exams.get(i + 1);  // Examen siguiente con el mismo nombre

                        for (Map.Entry<Student, List<Integer>> entry : exam.getGrades().entrySet()) {
                            Student student = entry.getKey();
                            int firstDigit = Integer.parseInt(String.valueOf(Math.abs(nextExam.getFinalGrade(student))).substring(0, 1));
                            if (firstDigit>exam.getFinalGrade(student)) {
                                // Imprimimos la información del examen actual
                                String currentExamLine = String.join(",", subject.getName(), exam.getExamName(), student.getName(), String.valueOf(exam.getFinalGrade(student)));
                                bw.write(currentExamLine);
                                bw.newLine();
                                // Imprimimos la información del siguiente examen
                                String nextExamLine = String.join(",", subject.getName(), nextExam.getExamName(), student.getName(), String.valueOf(nextExam.getFinalGrade(student)));
                                bw.write(nextExamLine);
                                bw.newLine();
                            }
                            else{
                                // Imprimimos la información del siguiente examen
                                String nextExamLine = String.join(",", subject.getName(), nextExam.getExamName(), student.getName(), String.valueOf(nextExam.getFinalGrade(student)));
                                bw.write(nextExamLine);
                                bw.newLine();
                                // Imprimimos la información del examen actual
                                String currentExamLine = String.join(",", subject.getName(), exam.getExamName(), student.getName(), String.valueOf(exam.getFinalGrade(student)));
                                bw.write(currentExamLine);
                                bw.newLine();
                            }
                        }

                        // Saltar el examen siguiente ya que ya lo procesamos
                        i++;
                    } else {
                        // Si el siguiente examen no tiene el mismo nombre, imprimimos solo el actual
                        for (Map.Entry<Student, List<Integer>> entry : exam.getGrades().entrySet()) {
                            Student student = entry.getKey();
                            String line1 = String.join(",",
                                    subject.getName(), exam.getExamName(), student.getName(),
                                    String.valueOf(exam.getFinalGrade(student))
                            );
                            bw.write(line1);
                            bw.newLine();
                        }
                    }
                }
            }

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e){
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }

    public void expected3(){
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))) {

            String line;

            String header = String.join(",","Subject_Name","Student_Name","Passed");
            bw.write(header);
            bw.newLine();
            br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] data = line.split(",");
                String subjectName = data[0];
                String criteriaType = data[1];
                float criteriaValue = Float.parseFloat(data[2]);
                String examName = data[3];
                List<Examination> examCriteriaList = new ArrayList<>();

                List<String> stringExamList = new ArrayList<>(); // Se agregan los nombres de los examenes a la lista auxiliar para despues compararlos con los originales
                for (int i = 3; i < data.length; i++) {
                    stringExamList.add(data[i].trim());
                }

                CriteriaCreator criteriaCreator = new CriteriaCreator(subjectName,stringExamList,criteriaType,criteriaValue);
                criteriaCreator.criteriaAdder();

            }

            University.addStudentsToAllSubjects();

            for (Subject subject : University.subjects) {
                for (Student student : subject.getStudentList()) {
                    String lineas = String.join(",", subject.getName(), student.getName(),String.valueOf(subject.passed(student)));
                    bw.write(lineas);
                    bw.newLine();
                }
            }

        }
        catch (FileNotFoundException e) {
                throw new RuntimeException(e);
        } catch (IOException e) {
                throw new RuntimeException(e);
        } catch (NumberFormatException e){
                throw new RuntimeException(e);
        } catch (IndexOutOfBoundsException e){
            throw new RuntimeException(e);
        }
    }
}
