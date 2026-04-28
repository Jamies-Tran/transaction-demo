package com.example.transactionlogdemo.infrastructure.persistence.mapper.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.EntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.TransactionCollection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructGlobalConfig.class)
public interface TransactionEntityMapper extends EntityMapper<TransactionCollection, Transaction> {
    void update(@MappingTarget TransactionCollection entity, Transaction domain);
}
