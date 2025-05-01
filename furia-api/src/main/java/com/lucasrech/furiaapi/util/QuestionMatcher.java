package com.lucasrech.furiaapi.util;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe para encontrar a melhor correspondência de perguntas. Utiliza o algoritmo de Levenshtein com aproximação de distância por similaridade.
 * A classe também normaliza as strings para remover acentos e caracteres especiais.

 * @version 1.0
 *
 */
public class QuestionMatcher {

    private static final int MAX_DISTANCE = 18;
    private static final double MIN_SIMILARITY = 0.90;


    public static String findBestMatch(Map<String, String> quotes, String question) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        String bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;

        for(String quote : quotes.keySet()) {
            int distance = levenshtein.apply(normalize(question), normalize(quote));
            double similarity = calculateCosineSimilarity(normalize(question), normalize(quote));

            if (distance < bestDistance && distance <= MAX_DISTANCE && similarity >= MIN_SIMILARITY) {
                bestDistance = distance;
                bestMatch = quotes.get(quote);
            }
        }

        return bestMatch;
    }

    public static String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .trim();
    }

    private static double calculateCosineSimilarity(String str1, String str2) {
        Map<Character, Integer> freq1 = getCharFrequency(str1);
        Map<Character, Integer> freq2 = getCharFrequency(str2);

        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (char c : freq1.keySet()) {
            dotProduct += freq1.getOrDefault(c, 0) * freq2.getOrDefault(c, 0);
            norm1 += Math.pow(freq1.get(c), 2);
        }

        for (int value : freq2.values()) {
            norm2 += Math.pow(value, 2);
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    private static Map<Character, Integer> getCharFrequency(String str) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : str.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        return frequency;
    }
}
