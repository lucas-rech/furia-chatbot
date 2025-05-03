package com.lucasrech.furiaapi.exceptions;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException() {
        super("Input value cannot be empty");
    }
}
