package com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.transaction.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRequestCollection {
    String params;
    String body;
    List<String> pathVariable;
}
