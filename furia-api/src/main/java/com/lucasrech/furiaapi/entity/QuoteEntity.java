package com.lucasrech.furiaapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class QuoteEntity {

    @Id
    private Long id;

    @Column(unique = true, nullable = false, length = 500)
    private String question;

    @Column(nullable = false, length = 500)
    private String answer;

    @Column(length = 60)
    private String shortcut;



}
