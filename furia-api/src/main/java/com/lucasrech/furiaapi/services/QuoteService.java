package com.lucasrech.furiaapi.services;

import com.lucasrech.furiaapi.entity.QuoteEntity;
import com.lucasrech.furiaapi.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }


    public void addQuote(QuoteEntity newQuote) {
        if (newQuote != null) {
            quoteRepository.save(newQuote);
        }
    }

    public List<QuoteEntity> getQuotes() {
        return quoteRepository.findAll();
    }
}
