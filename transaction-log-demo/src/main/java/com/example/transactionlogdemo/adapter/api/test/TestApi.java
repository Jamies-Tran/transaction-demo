package com.example.transactionlogdemo.adapter.api.test;

import com.example.transactionlogdemo.application.dto.request.test.Test2Request;
import com.example.transactionlogdemo.application.dto.request.test.TestRequest;
import com.example.transactionlogdemo.application.dto.response.test.Test2Response;
import com.example.transactionlogdemo.application.dto.response.test.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/demo/test")
public interface TestApi {
    @PostMapping
    ResponseEntity<TestResponse> test(@RequestBody TestRequest request);

    @PostMapping("/2")
    ResponseEntity<Test2Response> test2(@RequestBody @Validated Test2Request request);

    @GetMapping("/3")
    ResponseEntity<Test2Response> test3(@RequestParam String email);
}
