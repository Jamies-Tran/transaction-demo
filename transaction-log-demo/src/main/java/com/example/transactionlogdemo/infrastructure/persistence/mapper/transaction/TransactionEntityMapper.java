package com.example.transactionlogdemo.infrastructure.persistence.mapper.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.EntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.TransactionDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructGlobalConfig.class)
public interface TransactionEntityMapper extends EntityMapper<TransactionDocument, Transaction> {
    void update(@MappingTarget TransactionDocument entity, Transaction domain);
}
