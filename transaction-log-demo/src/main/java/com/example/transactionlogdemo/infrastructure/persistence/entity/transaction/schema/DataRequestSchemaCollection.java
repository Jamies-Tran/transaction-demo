package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRequestSchemaCollection {
    Map<String, Object> params;
    Map<String, Object> body;
}
