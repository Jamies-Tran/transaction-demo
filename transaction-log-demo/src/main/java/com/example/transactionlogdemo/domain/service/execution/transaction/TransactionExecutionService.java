package com.example.transactionlogdemo.domain.service.execution.transaction;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.transaction.Transaction;

import java.util.List;

public interface TransactionExecutionService {
    void execute(List<String> transactionCodes, ExecutionContext context);
}
