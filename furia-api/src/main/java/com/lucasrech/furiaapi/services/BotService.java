package com.lucasrech.furiaapi.services;

import com.lucasrech.furiaapi.dtos.ShortcutResponseDTO;
import com.lucasrech.furiaapi.exceptions.EmptyInputException;
import com.lucasrech.furiaapi.util.QuestionMatcher;
import com.lucasrech.furiaapi.util.ReadFiles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BotService {


    private final GPAPIService gpapiService;
    private final QuoteService quoteService;

    @Value("${quotes.file.path}")
    private String quotesFilePath;


    public BotService(GPAPIService GPAPIService, QuoteService quoteService) {
        this.gpapiService = GPAPIService;
        this.quoteService = quoteService;
    }

    public String talkBot(String input) {
        if(input == null || input.isEmpty()) {
            throw new EmptyInputException();
        }
        String response = QuestionMatcher.findBestMatch(quoteService.getAllQuotes(), input);
        if (response != null) {
            return response;
        } else {
            return gpapiService.chatAPI(input);
        }
    }
}


