package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.repository.transaction.TransactionRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.TransactionCollection;
import com.example.transactionlogdemo.infrastructure.persistence.mapper.transaction.TransactionEntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria.MongoCriteria;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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
        Optional<TransactionCollection> exist = getExist(transaction.id());
        if (exist.isPresent()) {
            upsertTransaction = update(exist.get(), transaction);
        }
        if (exist.isEmpty()) {
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
                .operator(MongoCriteria.EnumOperator.EQUAL)
                .build();

        return Optional.ofNullable(mongoTemplate.findOne(criteria.buildQuery(), TransactionCollection.class))
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Transaction> findAllByCodeIn(List<String> codes) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.<String>builder()
                        .key("code")
                        .values(codes)
                        .build())
                .operator(MongoCriteria.EnumOperator.IN)
                .build();
        List<TransactionCollection> transactionCollections = mongoTemplate.find(criteria.buildQuery(), TransactionCollection.class);

        return entityMapper.toDomain(transactionCollections);
    }

    @Override
    public void deleteByCode(String transactionCode) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("code")
                        .value(transactionCode)
                        .build())
                .operator(MongoCriteria.EnumOperator.IN)
                .build();
        mongoTemplate.remove(criteria.buildQuery());
    }

    private Optional<TransactionCollection> getExist(String id) {
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        MongoCriteria mongoCriteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("id")
                        .value(id)
                        .build())
                .operator(MongoCriteria.EnumOperator.EQUAL)
                .build();

        return Optional.ofNullable(mongoTemplate.findOne(mongoCriteria.buildQuery(), TransactionCollection.class));
    }

    private Transaction save(Transaction transaction) {
        TransactionCollection prepareSaveCollection = entityMapper.toEntity(transaction);
        TransactionCollection saveCollection = mongoTemplate.save(prepareSaveCollection);

        return entityMapper.toDomain(saveCollection);
    }

    private Transaction update(TransactionCollection transactionCollection, Transaction transaction) {
        entityMapper.update(transactionCollection, transaction);
        TransactionCollection saveDocument = mongoTemplate.save(transactionCollection);

        return entityMapper.toDomain(saveDocument);
    }
}
