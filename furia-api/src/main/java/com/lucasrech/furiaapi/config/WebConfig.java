package com.lucasrech.furiaapi.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.url}")
    private String frontend;

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/talk")
                .allowedOrigins(frontend)
                .allowedMethods("POST")
                .allowCredentials(true);
        registry.addMapping("/shortcuts")
                .allowedOrigins(frontend)
                .allowedMethods("GET")
                .allowCredentials(true);
    }
}
