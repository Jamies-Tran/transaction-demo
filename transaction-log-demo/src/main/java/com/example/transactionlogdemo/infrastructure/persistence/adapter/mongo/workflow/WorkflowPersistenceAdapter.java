package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.repository.workflow.WorkflowRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.workflow.WorkflowDocument;
import com.example.transactionlogdemo.infrastructure.persistence.mapper.workflow.WorkflowEntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria.MongoCriteria;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkflowPersistenceAdapter implements WorkflowRepository {
    MongoTemplate mongoTemplate;

    WorkflowEntityMapper entityMapper;

    @Override
    public Workflow upsert(Workflow workflow) {
        Workflow upsertWorkflow = null;
        if (Objects.nonNull(workflow.id())) {
            upsertWorkflow = update(workflow);
        }

        if (Objects.isNull(workflow.id())) {
            upsertWorkflow = save(workflow);
        }

        return upsertWorkflow;
    }

    private Workflow save(Workflow workflow) {
        WorkflowDocument prepareSaveDocument = entityMapper.toEntity(workflow);
        WorkflowDocument saveDocument = mongoTemplate.save(prepareSaveDocument);

        return entityMapper.toDomain(saveDocument);
    }

    private Workflow update(Workflow workflow) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("id")
                        .value(workflow.id())
                        .build())
                .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                .build();
        Optional<WorkflowDocument> workflowDocument = Optional
                .ofNullable(mongoTemplate.findOne(criteria.buildQuery(), WorkflowDocument.class));
        if (workflowDocument.isPresent()) {
            WorkflowDocument prepareSaveDocument = workflowDocument.get();
            entityMapper.update(prepareSaveDocument, workflow);
            WorkflowDocument saveDocument = mongoTemplate.save(prepareSaveDocument);

            return entityMapper.toDomain(saveDocument);
        }

        return null;
    }

    @Override
    public Optional<Workflow> findById(String id) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("id")
                        .value(id)
                        .build())
                .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                .build();

        return Optional
                .ofNullable(mongoTemplate.findOne(criteria.buildQuery(), WorkflowDocument.class))
                .map(entityMapper::toDomain);
    }

    @Override
    public void delete(String id) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("id")
                        .value(id)
                        .build())
                .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                .build();
        mongoTemplate.remove(criteria.buildQuery());
    }
}
