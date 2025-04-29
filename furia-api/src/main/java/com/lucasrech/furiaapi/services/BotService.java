package com.lucasrech.furiaapi.services;

import com.lucasrech.furiaapi.util.ReadFiles;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BotService {


    private final GPAPIService gpapiService;


    public BotService(GPAPIService GPAPIService) {
        this.gpapiService = GPAPIService;

    }

    public String talkBot(String input) {
        String response = getQuotes().get(input.toLowerCase());
        System.out.println(response);
        if (response != null) {
            return response;
        } else {
            try {
                return gpapiService.chatAPI(input);
            } catch (Exception e) {
                return "Desculpe, não consegui processar sua solicitação.";
            }
        }
    }

    private HashMap<String, String> getQuotes() {
        HashMap<String, String> quotes = new HashMap<>();
        ReadFiles.readQuotesFile("src/main/resources/static/quotes.csv", quotes);
        return quotes;
    }
}


