package com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.transaction.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRequest {
    String params;
    String body;
}
