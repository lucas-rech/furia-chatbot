package com.lucasrech.furiaapi.services;

import com.lucasrech.furiaapi.entity.QuoteEntity;
import com.lucasrech.furiaapi.repository.QuoteRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }


    public void addQuote(QuoteEntity newQuote) {
        if (newQuote == null) {
            throw new ServiceException("Null quote");
        } else if(quoteRepository.findByShortcut(newQuote.getShortcut()) != null) {
            throw new ServiceException("A quote with the shortcut already exists");
        }

        quoteRepository.save(newQuote);
    }

    public List<QuoteEntity> getQuotesWithShortcut() {
        return quoteRepository.findAllQuotesWithShortcut();
    }

    public List<QuoteEntity> getAllQuotes() {
        return quoteRepository.findAll();
    }
}
