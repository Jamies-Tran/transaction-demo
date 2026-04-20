package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema;

import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema.ipo.IpoDataProcessingDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSchemaDocument {
    Map<String, Object> params;
    Map<String, Object> body;
}
