package com.example.transactionlogdemo.application.usecase.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.repository.transaction.TransactionRepository;
import com.example.transactionlogdemo.domain.service.transaction.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionUseCase implements TransactionService {
    TransactionRepository repository;

    @Override
    public Transaction create(Transaction transaction) {
        return repository.upsert(transaction);
    }

    @Override
    public Optional<Transaction> getByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public List<Transaction> getAllByCodeIn(List<String> codes) {
        return repository.findAllByCodeIn(codes);
    }

    @Override
    public void removeByCode(String code) {
        repository.deleteByCode(code);
    }
}
