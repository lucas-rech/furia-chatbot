package com.lucasrech.furiaapi.exceptions;

public class TimeOutProcessingQuestion extends RuntimeException {
    public TimeOutProcessingQuestion() {

        super("Time over processing question");
    }
}
