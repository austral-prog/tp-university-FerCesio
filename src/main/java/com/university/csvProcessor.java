package com.university;

import com.university.criteria.AverageAboveValue;
import com.university.criteria.MaxAboveValue;
import com.university.criteria.MinAboveValue;
import com.university.examTypes.FinalPracticalWork;
import com.university.examTypes.OralExam;
import com.university.examTypes.PracticalWork;
import com.university.examTypes.WrittenExam;

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
                Examination exam1 = null;

                if (evaluationType.equals("WRITTEN_EXAM")){
                    exam1 = new WrittenExam(evaluationName,evaluationType);
                }
                else if (evaluationType.equals("ORAL_EXAM")){
                    exam1 = new OralExam(evaluationName,evaluationType);
                }
                else if (evaluationType.equals("PRACTICAL_WORK")){
                    exam1 = new PracticalWork(evaluationName,evaluationType);
                }
                else if (evaluationType.equals("FINAL_PRACTICAL_WORK")){
                    exam1 = new FinalPracticalWork(evaluationName,evaluationType);
                }


                for (Subject subject1 : University.subjects) {
                    if(subject1.getName().equals(subject)) {
                        if (!subject1.examMap.containsKey(evaluationName)) {
                            List<Examination> lst = new ArrayList<>();
                            lst.add(exam1);
                            subject1.examMap.put(evaluationName, lst);
                        }
                        if (exam1.equals(subject1.examMap.get(evaluationName).getFirst())) {
                            subject1.examList.add(exam1);
                        } else {
                            if (subject1.examMap.get(evaluationName).getFirst().getExamName().equals(exam1.getExamName()) && !subject1.examMap.get(evaluationName).getFirst().getType().equals(exam1.getType())) {
                                if (subject1.examMap.get(evaluationName).size()==1){
                                    subject1.examMap.get(evaluationName).add(exam1);
                                    subject1.examList.add(exam1);
                                }
                            }
                        }
                    }
                }

                for (Subject subject1 : University.subjects) {
                    if(subject1.getName().equals(subject)) {
                        for (Examination exam : subject1.examList) {
                            if (exam.getExamName().equals(evaluationName) && exam.getType().equals(evaluationType)) {
                                for (Student student1 : University.students) {
                                    List<Integer> gradelist = new ArrayList<>();
                                    if (student1.getName().equals(studentName)) {
                                        if (!exam.grades.containsKey(student1)) {
                                            gradelist.add(grade);
                                            exam.grades.put(student1, gradelist);
                                            exam.students.add(student1);
                                        }
                                        else {
                                            exam.grades.get(student1).add(grade);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Por cada examen, consegiur la instancia de estudiante del map y ponerlo

            }

            for (Subject subject : University.subjects) {
                subject.ExamComparator();
                List<Examination> exams = subject.examList;

                for (int i = 0; i < exams.size(); i++) {
                    Examination exam = exams.get(i);

                    // Verificamos si el examen actual tiene el mismo nombre que el siguiente
                    if (i < exams.size() - 1 && exam.getExamName().equals(exams.get(i + 1).getExamName())) {
                        Examination nextExam = exams.get(i + 1);  // Examen siguiente con el mismo nombre

                        for (Map.Entry<Student, List<Integer>> entry : exam.grades.entrySet()) {
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
                        for (Map.Entry<Student, List<Integer>> entry : exam.grades.entrySet()) {
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


                // Compara examenes y agrega la instancia ya existente a los criterios a tener en cuenta

                for (Subject subject1 : University.subjects) {
                    if(subject1.getName().equals(subjectName)) {
                        for (String e : stringExamList) {
                            for (Examination exam : subject1.examList) {
                                if (exam.getExamName().equals(e)) {
                                    examCriteriaList.add(exam);
                                }

                            }
                        }
                    }
                }


                // Criteria adder
                for (Subject subject:University.subjects){
                    if(subject.getName().equals(subjectName)){
                        if (criteriaType.equals("MAX_ABOVE_VALUE")) {
                            subject.addCriteria(new MaxAboveValue(criteriaType,criteriaValue,examCriteriaList));
                        }
                        else if (criteriaType.equals("MIN_ABOVE_VALUE")) {
                            subject.addCriteria(new MinAboveValue(criteriaType,criteriaValue,examCriteriaList));
                        }
                        else if (criteriaType.equals("AVERAGE_ABOVE_VALUE")) {
                            subject.addCriteria(new AverageAboveValue(criteriaType,criteriaValue,examCriteriaList));
                        }
                    }
                }

            }

            for (Subject subject : University.subjects) {
                for(Examination exam : subject.examList) {
                    for (Map.Entry<Student, List<Integer>> entry : exam.grades.entrySet()){
                        Student student = entry.getKey();
                        if (!subject.studentList.contains(student)) {
                            subject.studentList.add(student);
                        }
                    }
                }
            }
            for (Subject subject : University.subjects) {
                for (Student student : subject.studentList) {
                    String lineas = String.join(",", subject.getName(), student.getName(),String.valueOf(subject.passed(student)));
                    bw.write(lineas);
                    bw.newLine();
                }
            }

            /*for (Subject subject1 : University.subjects) {
                for (Examination exam : subject1.examList) {
                    for (Map.Entry<Student, List<Integer>> entry : exam.grades.entrySet()) {
                        Student key = entry.getKey();
                        List<Integer> gradeList = entry.getValue();
                        String lineas = String.join(",", subject1.getName(), key.getName(),String.valueOf(subject1.passed(key)));
                        bw.write(lineas);
                        bw.newLine();
                    }
                }
            }*/


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
