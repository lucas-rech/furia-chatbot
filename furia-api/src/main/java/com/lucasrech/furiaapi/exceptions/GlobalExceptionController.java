package com.lucasrech.furiaapi.exceptions;

import com.lucasrech.furiaapi.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(TimeOutProcessingQuestion.class)
    public ResponseEntity<ErrorResponseDTO> timeOverException(TimeOutProcessingQuestion ex) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(
                new ErrorResponseDTO(ex.getMessage())
        );
    }

    @ExceptionHandler(FileNotReadedException.class)
    public ResponseEntity<ErrorResponseDTO> fileNotFoundException(FileNotReadedException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                new ErrorResponseDTO(ex.getMessage())
        );
    }

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<ErrorResponseDTO> emptyInputException(EmptyInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponseDTO(ex.getMessage())
        );
    }
}
