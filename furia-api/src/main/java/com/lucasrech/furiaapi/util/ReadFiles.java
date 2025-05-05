package com.lucasrech.furiaapi.util;

import com.lucasrech.furiaapi.exceptions.FileNotReadedException;
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
                if (line.length == 3) {
                    String pergunta = line[0].trim();
                    String resposta = line[1].trim();
                    quotes.put(pergunta, resposta);
                } else if (line.length == 2) {
                    String pergunta = line[0].trim();
                    String resposta = line[1].trim();
                    quotes.put(pergunta, resposta);
                }
            }
        } catch (IOException | CsvValidationException e) {
             throw new FileNotReadedException("Error reading quotes file.");
        }
    }

    public static void readShortcutsFile(String filePath, HashMap<String, String> shortcuts) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length == 3) {
                    String key = line[0].trim();
                    String value = line[2].trim();
                    shortcuts.put(key, value);
                }
            }
        } catch (IOException | CsvValidationException e) {
             throw new FileNotReadedException("Error reading shortcuts file.");
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
            throw new FileNotReadedException("Error reading prompt file.");
        }
        return prompt.toString();
    }

}
