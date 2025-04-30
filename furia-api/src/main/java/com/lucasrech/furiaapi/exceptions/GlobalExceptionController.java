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
}
