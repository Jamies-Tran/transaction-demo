package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.repository.transaction.TransactionRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.TransactionDocument;
import com.example.transactionlogdemo.infrastructure.persistence.mapper.transaction.TransactionEntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria.MongoCriteria;
import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.transaction.MongoTransactionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionPersistenceAdapter implements TransactionRepository {
    MongoTemplate mongoTemplate;

    TransactionEntityMapper entityMapper;

    @Override
    public Transaction upsert(Transaction transaction) {
        Transaction upsertTransaction = null;
        if (Objects.nonNull(transaction.id()) || Objects.nonNull(transaction.code())) {
            upsertTransaction = update(transaction);
        }
        if (Objects.isNull(upsertTransaction)) {
            upsertTransaction = save(transaction);
        }

        return upsertTransaction;
    }

    @Override
    public Optional<Transaction> findByCode(String code) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("code")
                        .value(code)
                        .build())
                .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                .build();

        return Optional.ofNullable(mongoTemplate.findOne(criteria.buildQuery(), TransactionDocument.class))
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Transaction> findAllByCodeIn(List<String> codes) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.<String>builder()
                        .key("code")
                        .values(codes)
                        .build())
                .compareOperator(MongoCriteria.EnumCompareOperator.IN)
                .build();
        List<TransactionDocument> transactionDocuments = mongoTemplate.find(criteria.buildQuery(), TransactionDocument.class);

        return entityMapper.toDomain(transactionDocuments);
    }

    @Override
    public void deleteByCode(String transactionCode) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("code")
                        .value(transactionCode)
                        .build())
                .compareOperator(MongoCriteria.EnumCompareOperator.IN)
                .build();
        mongoTemplate.remove(criteria.buildQuery());
    }

    private Transaction save(Transaction transaction) {
        TransactionDocument prepareSaveDocument = entityMapper.toEntity(transaction);
        TransactionDocument saveDocument = mongoTemplate.save(prepareSaveDocument);

        return entityMapper.toDomain(saveDocument);
    }

    private Transaction update(Transaction transaction) {
        MongoCriteria.MongoCriteriaBuilder criteriaBuilder = MongoCriteria.builder();
        if (Objects.nonNull(transaction.id())) {
            criteriaBuilder
                    .field(MongoCriteria.Field.builder()
                            .key("id")
                            .value(transaction.id())
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL);
        }
        if (Objects.nonNull(transaction.code())) {
            criteriaBuilder
                    .field(MongoCriteria.Field.builder()
                            .key("code")
                            .value(transaction.code())
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL);
        }
        if (Objects.nonNull(transaction.id()) && Objects.nonNull(transaction.code())) {
            criteriaBuilder
                    .field(MongoCriteria.Field.builder()
                            .key("id")
                            .value(transaction.id())
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                    .or(List.of(MongoCriteria.builder()
                            .field(MongoCriteria.Field.builder()
                                    .key("code")
                                    .value(transaction.code())
                                    .build())
                            .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                            .build()));
        }
        MongoCriteria criteria = criteriaBuilder.build();
        Optional<TransactionDocument> t = Optional.ofNullable(mongoTemplate.findOne(criteria.buildQuery(),
                TransactionDocument.class));
        if (t.isPresent()) {
            TransactionDocument prepareSaveDocument = t.get();
            entityMapper.update(prepareSaveDocument, transaction);
            TransactionDocument saveDocument = mongoTemplate.save(prepareSaveDocument);
            return entityMapper.toDomain(saveDocument);
        }

        return null;
    }
}
