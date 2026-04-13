package com.example.transactionlogdemo.domain.repository.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction upsert(Transaction transaction);

    Optional<Transaction> findByCode(String code);

    List<Transaction> findAllByCodeIn(List<String> codes);

    void deleteByCode(String code);
}
