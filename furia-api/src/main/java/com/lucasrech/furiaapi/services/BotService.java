package com.lucasrech.furiaapi.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BotService {


    private final OpenAIService openAIService;

    private final HashMap<String, String> responses = new HashMap<>(
            Map.of(
                    "oi", "Oi, tudo bem?",
                    "tudo bem?", "Tudo ótimo, e você?",
                    "qual seu nome?", "Meu nome é PANTO.",
                    "quem é você?", "Eu sou o PANTO, um bot de exemplo.",
                    "o que você faz?", "Eu sou um bot de exemplo, posso responder perguntas simples.",
                    "qual é a sua cor favorita?", "Minha cor favorita é preto!.",
                    "qual é o seu time?", "Eu sou do time da Furia!"
            )
    );

    public BotService(OpenAIService openAIService) {
        this.openAIService = openAIService;

    }

    public String talkBotAPI(String input) {
        String chatResponse = openAIService.chatAPI(input);
        System.out.println(chatResponse);
        return chatResponse;
    }
}
