package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponseSchemaDocument {
    Map<String, Object> body;
}
