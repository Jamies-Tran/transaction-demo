package com.example.transactionlogdemo.application.dto.response.transaction.authentication;

public record AuthenticationResponse(
        String type,
        String token
) {
}
