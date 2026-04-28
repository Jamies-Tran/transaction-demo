package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.repository.workflow.WorkflowRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.workflow.WorkflowCollection;
import com.example.transactionlogdemo.infrastructure.persistence.mapper.workflow.WorkflowEntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria.MongoCriteria;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
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
        Optional<WorkflowCollection> exist = getExist(workflow.id());
        if (exist.isPresent()) {
            upsertWorkflow = update(exist.get(), workflow);
        }

        if (exist.isEmpty()) {
            upsertWorkflow = save(workflow);
        }

        return upsertWorkflow;
    }

    @Override
    public Optional<Workflow> findByCode(String code) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("code")
                        .value(code)
                        .build())
                .operator(MongoCriteria.EnumOperator.EQUAL)
                .build();

        return Optional
                .ofNullable(mongoTemplate.findOne(criteria.buildQuery(), WorkflowCollection.class))
                .map(entityMapper::toDomain);
    }

    @Override
    public void deleteByCode(String code) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("code")
                        .value(code)
                        .build())
                .operator(MongoCriteria.EnumOperator.EQUAL)
                .build();

        mongoTemplate.remove(criteria.buildQuery());
    }

    private Optional<WorkflowCollection> getExist(String id) {
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

        return Optional.ofNullable(mongoTemplate.findOne(mongoCriteria.buildQuery(), WorkflowCollection.class));
    }

    private Workflow save(Workflow workflow) {
        WorkflowCollection prepareSaveCollection = entityMapper.toEntity(workflow);
        WorkflowCollection savedCollection = mongoTemplate.save(prepareSaveCollection);

        return entityMapper.toDomain(savedCollection);
    }

    private Workflow update(WorkflowCollection workflowCollection, Workflow workflow) {
        entityMapper.update(workflowCollection, workflow);
        WorkflowCollection savedCollection = mongoTemplate.save(workflowCollection);

        return entityMapper.toDomain(savedCollection);
    }
}
