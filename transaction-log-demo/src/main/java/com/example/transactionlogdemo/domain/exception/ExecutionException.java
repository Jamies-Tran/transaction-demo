package com.example.transactionlogdemo.domain.exception;

public class ExecutionException extends RuntimeException {
    public ExecutionException(String message) {
        super(message);
    }

    public ExecutionException() {
        super();
    }
}
