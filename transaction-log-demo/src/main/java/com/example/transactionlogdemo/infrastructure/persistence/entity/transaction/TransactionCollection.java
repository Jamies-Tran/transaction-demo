package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction;

import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.authentication.AuthenticationCollection;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.retry.RetryCollection;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema.DataRequestSchemaCollection;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema.DataResponseSchemaCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("transactions")
public class TransactionCollection {
    String id;
    String routeCode;
    String code;
    String name;
    DataRequestSchemaCollection requestSchema;
    DataResponseSchemaCollection responseSchema;
    AuthenticationCollection authentication;
    RetryCollection retry;
    List<String> dependsOn;
}
