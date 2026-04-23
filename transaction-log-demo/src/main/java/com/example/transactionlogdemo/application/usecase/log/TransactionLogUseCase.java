package com.example.transactionlogdemo.application.usecase.log;

import com.example.transactionlogdemo.domain.entity.log.TransactionLog;
import com.example.transactionlogdemo.domain.entity.log.TransactionLogCriteria;
import com.example.transactionlogdemo.domain.repository.log.TransactionLogRepository;
import com.example.transactionlogdemo.domain.service.log.TransactionLogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionLogUseCase implements TransactionLogService {
    TransactionLogRepository repository;

    @Override
    public TransactionLog create(TransactionLog transactionLog) {
        return repository.save(transactionLog);
    }

    @Override
    public Page<TransactionLog> getAll(TransactionLogCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest);
    }
}
