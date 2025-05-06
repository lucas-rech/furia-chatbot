package com.lucasrech.furiaapi.controller;

import com.lucasrech.furiaapi.dtos.QuoteDTO;
import com.lucasrech.furiaapi.dtos.ShortcutResponseDTO;
import com.lucasrech.furiaapi.entity.QuoteEntity;
import com.lucasrech.furiaapi.services.QuoteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<?> createQuote(@RequestBody QuoteDTO newQuote) {
        QuoteEntity newQuoteEntity;
        if(newQuote.shortcut() != null) {
            newQuoteEntity = new QuoteEntity(newQuote.question(), newQuote.answer(), newQuote.shortcut());

        } else {
            newQuoteEntity = new QuoteEntity(newQuote.question(), newQuote.answer());
        }

        quoteService.addQuote(newQuoteEntity);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(newQuoteEntity);
    }

    @GetMapping
    public ResponseEntity<List<ShortcutResponseDTO>> getQuotes() {
        List<QuoteEntity> responseEntities = quoteService.getQuotesWithShortcut();

        List<ShortcutResponseDTO> quotesDTO = new ArrayList<>();
        for (QuoteEntity quoteEntity : responseEntities) {
            quotesDTO.add(new ShortcutResponseDTO(quoteEntity.getShortcut(), quoteEntity.getAnswer()));
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(quotesDTO);
    }

}
