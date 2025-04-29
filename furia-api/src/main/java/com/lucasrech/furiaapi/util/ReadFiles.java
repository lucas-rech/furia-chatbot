package com.lucasrech.furiaapi.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadFiles {


    public static void readQuotesFile(String filePath, HashMap<String, String> quotes) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length == 2) {
                    String pergunta = line[0].trim();
                    String resposta = line[1].trim();
                    quotes.put(pergunta, resposta);
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static String readPromptFile(String filePath) {
        StringBuilder prompt = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                prompt.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return prompt.toString();
    }

}
