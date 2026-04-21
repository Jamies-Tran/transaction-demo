package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction;

import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.retry.RetryDocument;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema.DataRequestSchemaDocument;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.authentication.AuthenticationDocument;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema.DataResponseSchemaDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("transactions")
public class TransactionDocument {
    String id;
    String routeCode;
    String code;
    String name;
    DataRequestSchemaDocument requestSchema;
    DataResponseSchemaDocument responseSchema;
    AuthenticationDocument authentication;
    RetryDocument retry;
    List<String> dependsOn;
}
