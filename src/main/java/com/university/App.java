package com.university;

import com.university.mainManagement.csvProcessor;

public class App {
    public static void main(String[] args){
        String archivoEntrada = "src/main/resources/input.csv";  // Archivo CSV de entrada
        String archivoSalida = "src/main/resources/solution.csv";  // Archivo CSV de salida
        String archivoEntrada2 = "src/main/resources/input_2.csv";
        String archivoSalida2 = "src/main/resources/solution2.csv";
        String archivoEntrada3 = "src/main/resources/input_3.csv";
        String archivoSalida3 = "src/main/resources/solution3.csv";

        csvProcessor Part1 = new csvProcessor(archivoEntrada, archivoSalida);
        Part1.expected1();
        csvProcessor Part2 = new csvProcessor(archivoEntrada2, archivoSalida2);
        Part2.expected2();
        csvProcessor Part3 = new csvProcessor(archivoEntrada3, archivoSalida3);
        Part3.expected3();
    }
}
