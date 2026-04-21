package com.example.transactionlogdemo.adapter.api.test;

import com.example.transactionlogdemo.application.dto.request.test.TestRequest;
import com.example.transactionlogdemo.application.dto.response.test.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/demo/test")
public interface TestApi {
    @PostMapping
    ResponseEntity<TestResponse> test(@RequestBody TestRequest request);
}
