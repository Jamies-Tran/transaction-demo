package com.example.transactionlogdemo.application.mapper.response.transaction;

import com.example.transactionlogdemo.application.dto.response.transaction.TransactionResponse;
import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface TransactionResponseMapper extends DtoMapper<TransactionResponse, Transaction> {
}
