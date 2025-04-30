package com.lucasrech.furiaapi.exceptions;

public class NotMatchQuestion extends RuntimeException {
    public NotMatchQuestion() {

        super("No match found for the question.");
    }
}
