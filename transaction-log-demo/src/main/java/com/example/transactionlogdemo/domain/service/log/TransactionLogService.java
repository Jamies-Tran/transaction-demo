package com.example.transactionlogdemo.domain.service.log;

import com.example.transactionlogdemo.domain.entity.log.TransactionLog;
import org.springframework.data.domain.Page;

public interface TransactionLogService {
    TransactionLog create(TransactionLog transactionLog);

    Page<TransactionLog> findAll();
}
