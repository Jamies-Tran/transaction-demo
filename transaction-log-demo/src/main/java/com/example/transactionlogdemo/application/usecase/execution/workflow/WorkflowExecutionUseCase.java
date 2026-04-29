package com.example.transactionlogdemo.application.usecase.execution.workflow;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.service.execution.execution.result.workflow.WorkflowExecutionResultService;
import com.example.transactionlogdemo.domain.service.execution.transaction.TransactionExecutionService;
import com.example.transactionlogdemo.domain.service.execution.workflow.WorkflowExecutionService;
import com.example.transactionlogdemo.domain.service.workflow.WorkflowService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkflowExecutionUseCase implements WorkflowExecutionService {
    WorkflowService workflowService;

    TransactionExecutionService transactionExecutionService;

    WorkflowExecutionResultService workflowExecutionResultService;

    @Override
    public WorkflowExecutionResult execute(String workflowCode, Map<String, Object> input) {
        ExecutionContext context = new ExecutionContext();
        context.putAll(input);
        Workflow workflow = workflowService.getByCode(workflowCode)
                .orElseThrow(RuntimeException::new);

        List<WorkflowExecutionResult.ExecutionResult> executionResults =
                transactionExecutionService.execute(workflow.transactions(), context);
        WorkflowExecutionResult workflowExecutionResult = workflowExecutionResultService
                .create(WorkflowExecutionResult.builder()
                        .workflowCode(workflowCode)
                        .executionResults(executionResults)
                        .build());
        log.info("Current context: {}", context.getData());

        return workflowExecutionResult;
    }
}
