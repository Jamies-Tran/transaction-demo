package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.log;

import com.example.transactionlogdemo.domain.entity.log.TransactionLog;
import com.example.transactionlogdemo.domain.entity.log.TransactionLogCriteria;
import com.example.transactionlogdemo.domain.repository.log.TransactionLogRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.log.TransactionLogCollection;
import com.example.transactionlogdemo.infrastructure.persistence.mapper.log.TransactionLogEntityMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionLogPersistenceAdapter implements TransactionLogRepository {
    MongoTemplate mongoTemplate;

    TransactionLogEntityMapper transactionLogEntityMapper;

    @Override
    public TransactionLog save(TransactionLog transactionLog) {
        TransactionLogCollection prepareSaveLog = transactionLogEntityMapper.toEntity(transactionLog);
        TransactionLogCollection savedLog = mongoTemplate.save(prepareSaveLog);

        return transactionLogEntityMapper.toDomain(savedLog);
    }

    @Override
    public Page<TransactionLog> findAll(TransactionLogCriteria criteria, PageRequest pageRequest) {
        Query query = criteria.buildQuery(pageRequest);
        List<TransactionLogCollection> transactionLogs = mongoTemplate
                .find(query, TransactionLogCollection.class);
        long total = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), TransactionLogCollection.class);

        return new PageImpl<>(transactionLogEntityMapper.toDomain(transactionLogs), pageRequest, total);
    }
}
