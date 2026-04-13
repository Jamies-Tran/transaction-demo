package com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.transaction;

import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.TransactionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoTransactionRepository extends MongoRepository<TransactionDocument, String> {

}
