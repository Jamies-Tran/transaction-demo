package com.example.transactionlogdemo.application.usecase.execution.workflow.result;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.domain.repository.execution.result.workflow.WorkflowExecutionResultRepository;
import com.example.transactionlogdemo.domain.service.execution.execution.result.workflow.WorkflowExecutionResultService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkflowExecutionResultUseCase implements WorkflowExecutionResultService {
    WorkflowExecutionResultRepository repository;


    @Override
    public WorkflowExecutionResult create(WorkflowExecutionResult workflowExecutionResult) {
        return repository.save(workflowExecutionResult);
    }

    @Override
    public Optional<WorkflowExecutionResult> getByCode(String code) {
        return repository.findByCode(code);
    }
}
