package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDocument {
    String type;
    String token;
}
