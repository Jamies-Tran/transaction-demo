package com.example.transactionlogdemo.domain.repository.log;

import com.example.transactionlogdemo.domain.entity.log.TransactionLog;
import com.example.transactionlogdemo.domain.entity.log.TransactionLogCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TransactionLogRepository {
    TransactionLog save(TransactionLog transactionLog);

    Page<TransactionLog> findAll(TransactionLogCriteria criteria, PageRequest pageRequest);
}
