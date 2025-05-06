package com.lucasrech.furiaapi;

import com.lucasrech.furiaapi.entity.QuoteEntity;
import com.lucasrech.furiaapi.repository.QuoteRepository;
import com.lucasrech.furiaapi.services.QuoteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FuriaApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FuriaApiApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(FuriaApiApplication.class, args);
    }

}
