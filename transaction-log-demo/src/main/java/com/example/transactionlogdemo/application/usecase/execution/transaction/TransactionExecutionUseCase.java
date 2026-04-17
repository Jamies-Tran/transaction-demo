package com.example.transactionlogdemo.application.usecase.execution.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.service.execution.transaction.TransactionExecutionService;
import com.example.transactionlogdemo.domain.service.transaction.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionExecutionUseCase implements TransactionExecutionService {
    TransactionService transactionService;

    @Override
    public void execute(List<String> transactionCodes) {
        List<Transaction> transactions = transactionService.getAllByCodeIn(transactionCodes);


    }
}
