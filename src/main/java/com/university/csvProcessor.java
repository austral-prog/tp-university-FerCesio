package com.university;

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

                University.recieveData(student,subject);

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
                Examination exam1 = new Examination(evaluationType,evaluationName);


                for (Subject subject1 : University.subjects) {
                    if(subject1.getName().equals(subject)) {
                        if (!subject1.examMap.containsKey(evaluationName)) {
                            subject1.examMap.put(evaluationName, exam1);
                        }
                        if (exam1.equals(subject1.examMap.get(evaluationName))) {
                            subject1.examList.add(exam1);
                        }

                    }
                }

                for (Student student1 : University.students) {
                    List<Integer> gradesList = new ArrayList<>();
                    if (student1.getName().equals(studentName)) {
                        if (!exam1.grades.containsKey(student1)) {
                            gradesList.add(grade);
                            exam1.grades.put(student1, gradesList);
                        }
                        else {
                            exam1.grades.get(student1).add(grade);
                        }
                    }
                }


            }

            for (Subject subject : University.subjects) {
                for (Examination exam : subject.examList) {
                    for (Map.Entry<Student, List<Integer>> entry : exam.grades.entrySet()) {
                        Student key = entry.getKey();
                        List<Integer> gradeList = entry.getValue();
                        String lines = String.join(",", subject.getName(), exam.getExamName(), key.getName(), String.valueOf(average(gradeList)));
                        bw.write(lines);
                        bw.newLine();
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
        }
    }

    private float average(List<Integer> list){
        int total = 0;
        float average = 0;
        for (int number : list){
            total = total + number;
        }
        average = (float) total / list.size();
        return average;
    }
}
