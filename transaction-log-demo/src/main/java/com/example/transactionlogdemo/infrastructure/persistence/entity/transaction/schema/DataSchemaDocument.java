package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSchemaDocument {
    String scope;
    String field;
    String label;
}
