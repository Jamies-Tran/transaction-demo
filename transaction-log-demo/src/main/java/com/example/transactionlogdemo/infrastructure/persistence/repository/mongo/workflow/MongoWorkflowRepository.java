package com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.workflow;

import com.example.transactionlogdemo.infrastructure.persistence.entity.workflow.WorkflowDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoWorkflowRepository extends MongoRepository<WorkflowDocument, String> {
}
