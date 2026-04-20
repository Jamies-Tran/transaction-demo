package com.example.transactionlogdemo.application.usecase.execution.workflow;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.service.execution.transaction.TransactionExecutionService;
import com.example.transactionlogdemo.domain.service.execution.workflow.WorkflowExecutionService;
import com.example.transactionlogdemo.domain.service.transaction.TransactionService;
import com.example.transactionlogdemo.domain.service.workflow.WorkflowService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkflowExecutionUseCase implements WorkflowExecutionService {
    WorkflowService workflowService;

    TransactionExecutionService transactionExecutionService;

    @Override
    public void execute(String workflowId, Map<String, Object> input) {
        ExecutionContext context = new ExecutionContext();
        context.putAll(input);
        Workflow workflow = workflowService.getById(workflowId)
                .orElseThrow(RuntimeException::new);

        List<String> transactionCodes = workflow.transactionCodes();
        transactionExecutionService.execute(transactionCodes, context);
    }
}
