package com.example.transactionlogdemo.application.usecase.execution.workflow;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.service.execution.execution.result.workflow.WorkflowExecutionResultService;
import com.example.transactionlogdemo.domain.service.execution.transaction.TransactionExecutionService;
import com.example.transactionlogdemo.domain.service.execution.workflow.WorkflowExecutionService;
import com.example.transactionlogdemo.domain.service.workflow.WorkflowService;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.JsonPathUtils;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.ObjectMapperUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuppressWarnings("unchecked")
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

        List<WorkflowExecutionResult.ExecutionLog> executionLogs =
                transactionExecutionService.execute(workflow.transactions(), context);
        Map<String, Object> dataExecutionMapped = context.getData();
        if (!CollectionUtils.isEmpty(workflow.dataResultSchema())) {
            mapExecutionResult(workflow.dataResultSchema(), context.getData(),
                    dataExecutionMapped);
        }
        WorkflowExecutionResult workflowExecutionResult = workflowExecutionResultService
                .create(WorkflowExecutionResult.builder()
                        .workflowCode(workflowCode)
                        .executionResult(ObjectMapperUtils.convertToString(dataExecutionMapped))
                        .executionLogs(executionLogs)
                        .build());
        log.info("Current context: {}", context.getData());

        return workflowExecutionResult;
    }

    private void mapExecutionResult(
            Map<String, Object> mapping,
            Object source,
            Map<String, Object> result
    ) {
        for (Map.Entry<String, Object> entry : mapping.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String jsonPath) {
                Object valueMapped = JsonPathUtils.read(source, jsonPath);
                result.put(key, valueMapped);
            }
            if (value instanceof Map<?,?> map) {
                mapExecutionResult((Map<String, Object>) map, source, result);
            }
        }
    }
}
