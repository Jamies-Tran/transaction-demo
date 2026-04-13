package com.example.transactionlogdemo.domain.service.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction create(Transaction transaction);

    Optional<Transaction> getByCode(String code);

    List<Transaction> getAllByCodeIn(List<String> codes);

    void removeByCode(String code);
}
