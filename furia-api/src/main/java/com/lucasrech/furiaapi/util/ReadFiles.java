package com.lucasrech.furiaapi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadFiles {


    public static void readQuotesFile(String filePath, HashMap<String, String> quotes) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] columns = linha.replaceAll("\"", "").split(",", 2);

                if (columns.length == 2) {
                    String pergunta = columns[0].trim();
                    String resposta = columns[1].trim();
                    quotes.put(pergunta, resposta);
                }
            }
            //TODO: Adicionar tratamento de erro
        } catch (IOException e) {
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
