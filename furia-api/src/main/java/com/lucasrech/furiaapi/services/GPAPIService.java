package com.lucasrech.furiaapi.services;

import com.lucasrech.furiaapi.dtos.gpapi.CompletionResponse;
import com.lucasrech.furiaapi.exceptions.TimeOutProcessingQuestion;
import com.lucasrech.furiaapi.util.ReadFiles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GPAPIService {

    private final WebClient webClient;

    @Value("${prompt.api.path}")
    private String promptPath;

    @Value("${groq.api.key}")
    private String apiKey;

    public GPAPIService(WebClient.Builder webClientBuilder, @Value("${groq.api.url}") String apiUrl) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    public String chatAPI(String inputMessage) {

        Map<String, Object> requestBody = new HashMap<>();
        String prompt = ReadFiles.readPromptFile(promptPath);

        requestBody.put("model", "meta-llama/llama-4-scout-17b-16e-instruct");
        requestBody.put("messages", List.of(Map.of(
                "role", "user",
                "content",  prompt + inputMessage
        )));
        CompletionResponse response = webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(CompletionResponse.class)
                .block();

        if (response != null && response.choices() != null) {
            return response.choices().getFirst().message().content();
        } else {
            throw new TimeOutProcessingQuestion();
        }
    }
}
