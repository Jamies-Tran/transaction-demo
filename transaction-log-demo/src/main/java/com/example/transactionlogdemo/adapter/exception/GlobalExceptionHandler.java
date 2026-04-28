package com.example.transactionlogdemo.adapter.exception;


import com.example.transactionlogdemo.domain.exception.ExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<?> executionExceptionHandler(ExecutionException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
