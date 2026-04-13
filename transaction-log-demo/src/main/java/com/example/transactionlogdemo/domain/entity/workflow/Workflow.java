package com.example.transactionlogdemo.domain.entity.workflow;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;

import java.util.List;

public record Workflow(
        String id,
        String name,
        List<String> transactionCodes,
        Boolean active
) {
}
