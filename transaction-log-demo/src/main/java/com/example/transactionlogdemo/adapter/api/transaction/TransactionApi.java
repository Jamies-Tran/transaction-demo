package com.example.transactionlogdemo.adapter.api.transaction;

import com.example.transactionlogdemo.application.dto.request.transaction.TransactionCodeListRequest;
import com.example.transactionlogdemo.application.dto.request.transaction.TransactionRequest;
import com.example.transactionlogdemo.application.dto.response.transaction.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/demo/transaction")
public interface TransactionApi {
    @PostMapping
    ResponseEntity<TransactionResponse> create(@RequestBody TransactionRequest request);

    @GetMapping("/{code}")
    ResponseEntity<TransactionResponse> getByCode(@PathVariable String code);

    @PostMapping("/code-list")
    ResponseEntity<List<TransactionResponse>> getAllByCodeIn(@RequestBody TransactionCodeListRequest requestList);

    @DeleteMapping("/{code}")
    ResponseEntity<?> remove(@PathVariable String code);
}
