package com.example.transactionlogdemo.application.mapper.request.transaction;

import com.example.transactionlogdemo.application.dto.request.transaction.TransactionRequest;
import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface TransactionRequestMapper extends DtoMapper<TransactionRequest, Transaction> {
}
