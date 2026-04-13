package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema;

import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.schema.ipo.IpoDataProcessingDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSchemaDocument {
    List<IpoDataProcessingDocument> requestSchema;
    List<IpoDataProcessingDocument> responseSchema;
}
