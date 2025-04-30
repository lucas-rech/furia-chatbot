package com.lucasrech.furiaapi.exceptions;

public class FileNotReadedException extends RuntimeException {
    public FileNotReadedException(String message) {
        super(message);
    }
}
