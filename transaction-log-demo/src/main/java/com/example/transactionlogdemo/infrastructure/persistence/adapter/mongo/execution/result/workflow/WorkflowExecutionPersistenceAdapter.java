package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.execution.result.workflow;


import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.domain.repository.execution.result.workflow.WorkflowExecutionResultRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.workflow.WorkflowExecutionResultCollection;
import com.example.transactionlogdemo.infrastructure.persistence.mapper.execution.result.workflow.WorkflowExecutionResultEntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria.MongoCriteria;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkflowExecutionPersistenceAdapter implements WorkflowExecutionResultRepository {
    MongoTemplate mongoTemplate;

    WorkflowExecutionResultEntityMapper entityMapper;


    @Override
    public WorkflowExecutionResult save(WorkflowExecutionResult workflowExecutionResult) {
        WorkflowExecutionResultCollection prepareSaveCollection = entityMapper.toEntity(workflowExecutionResult);
        WorkflowExecutionResultCollection savedCollection = mongoTemplate.save(prepareSaveCollection);

        return entityMapper.toDomain(savedCollection);
    }

    @Override
    public Optional<WorkflowExecutionResult> findByCode(String code) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("code")
                        .value(code)
                        .build())
                .operator(MongoCriteria.EnumOperator.EQUAL)
                .build();

        return Optional.ofNullable(mongoTemplate.findOne(criteria.buildQuery(), WorkflowExecutionResult.class));
    }
}
