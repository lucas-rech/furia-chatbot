package com.lucasrech.furiaapi.services;

import com.lucasrech.furiaapi.dtos.ShortcutResponseDTO;
import com.lucasrech.furiaapi.util.QuestionMatcher;
import com.lucasrech.furiaapi.util.ReadFiles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BotService {


    private final GPAPIService gpapiService;

    @Value("${quotes.file.path}")
    private String quotesFilePath;


    public BotService(GPAPIService GPAPIService) {
        this.gpapiService = GPAPIService;

    }

    public String talkBot(String input) {
        String response = QuestionMatcher.findBestMatch(getQuotes(), input);
        if (response != null) {
            return response;
        } else {
            return gpapiService.chatAPI(input);
        }
    }

    private HashMap<String, String> getQuotes() {
        HashMap<String, String> quotes = new HashMap<>();
        ReadFiles.readQuotesFile(quotesFilePath, quotes);

        return quotes;
    }


    public ShortcutResponseDTO[]  getShortcuts() {
        HashMap<String, String> shortcuts = new HashMap<>();
        ReadFiles.readShortcutsFile(quotesFilePath, shortcuts);


        return shortcuts.entrySet().stream()
                .map(entry -> new ShortcutResponseDTO(entry.getKey(), entry.getValue()))
                .toArray(ShortcutResponseDTO[]::new);
    }
}


