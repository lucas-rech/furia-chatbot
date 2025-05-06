package com.lucasrech.furiaapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "quotes")
@Data //Getters and Setters
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 500)
    private String question;

    @Column(nullable = false, length = 500)
    private String answer;

    @Column(length = 60)
    private String shortcut;

    public QuoteEntity(String question, String answer, String shortcut) {
        this.question = question;
        this.answer = answer;
        this.shortcut = shortcut;
    }

    public QuoteEntity(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
