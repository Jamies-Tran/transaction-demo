package com.example.transactionlogdemo.adapter.controller.transaction;

import com.example.transactionlogdemo.adapter.api.transaction.TransactionApi;
import com.example.transactionlogdemo.application.dto.request.transaction.TransactionCodeListRequest;
import com.example.transactionlogdemo.application.dto.request.transaction.TransactionRequest;
import com.example.transactionlogdemo.application.dto.response.transaction.TransactionResponse;
import com.example.transactionlogdemo.application.mapper.transaction.TransactionRequestMapper;
import com.example.transactionlogdemo.application.mapper.transaction.TransactionResponseMapper;
import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.service.transaction.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionController implements TransactionApi {
    TransactionService transactionService;

    TransactionRequestMapper requestMapper;

    TransactionResponseMapper responseMapper;

    @Override
    public ResponseEntity<TransactionResponse> create(TransactionRequest request) {
        Transaction prepareCreate = requestMapper.toDomain(request);
        Transaction transaction = transactionService.create(prepareCreate);

        return ResponseEntity.ok(responseMapper.toDto(transaction));
    }

    @Override
    public ResponseEntity<TransactionResponse> getByCode(String code) {
        TransactionResponse response = transactionService.getByCode(code)
                .map(responseMapper::toDto)
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllByCodeIn(TransactionCodeListRequest requestList) {
        List<Transaction> transactions = transactionService.getAllByCodeIn(requestList.transactionCodes());

        return ResponseEntity.ok(responseMapper.toDto(transactions));
    }

    @Override
    public ResponseEntity<?> remove(String code) {
        transactionService.removeByCode(code);

        return ResponseEntity.noContent().build();
    }
}
