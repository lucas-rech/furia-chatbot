package com.lucasrech.furiaapi.services;

import com.lucasrech.furiaapi.dtos.grok.CompletionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {
    private final WebClient webClient;

    @Value("${groq.api.key}")
    private String apiKey;

    public OpenAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.groq.com/openai/v1").build();
    }

    public String chatAPI(String inputMessage) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "meta-llama/llama-4-scout-17b-16e-instruct");
        requestBody.put("messages", List.of(Map.of(
                "role", "user",
                "content", "Considere que você é um chatbot da Furia eSPorts, chamado Panto. Você é um assistente web e auxilia fãs especificamente do time de Counter Strike. Baseado nisso, responda: " + inputMessage
        )));
        CompletionResponse response = webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(CompletionResponse.class)
                .block();

        if (response != null && response.choices() != null) {
            return response.choices().getFirst().message().content();
        } else {
            return "Desculpe, não consegui processar sua solicitação.";
        }
    }
}
