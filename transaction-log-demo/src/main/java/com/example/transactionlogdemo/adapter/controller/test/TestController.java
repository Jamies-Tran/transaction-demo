package com.example.transactionlogdemo.adapter.controller.test;

import com.example.transactionlogdemo.adapter.api.test.TestApi;
import com.example.transactionlogdemo.application.dto.request.test.Test2Request;
import com.example.transactionlogdemo.application.dto.request.test.TestRequest;
import com.example.transactionlogdemo.application.dto.response.test.Test2Response;
import com.example.transactionlogdemo.application.dto.response.test.TestResponse;
import com.example.transactionlogdemo.application.mapper.request.test.Test2RequestMapper;
import com.example.transactionlogdemo.application.mapper.request.test.TestRequestMapper;
import com.example.transactionlogdemo.application.mapper.response.test.Test2ResponseMapper;
import com.example.transactionlogdemo.application.mapper.response.test.TestResponseMapper;
import com.example.transactionlogdemo.domain.entity.test.Test;
import com.example.transactionlogdemo.domain.entity.test.Test2;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestController implements TestApi {
    TestRequestMapper requestMapper;
    TestResponseMapper responseMapper;
    Test2ResponseMapper response2Mapper;
    Test2RequestMapper request2Mapper;

    @Override
    public ResponseEntity<TestResponse> test(TestRequest request) {

        log.info("Test receive: {}", request);
        Test test = requestMapper.toDomain(request);
        TestResponse response = responseMapper.toDto(test);
        log.info("Test response: {}", response);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Test2Response> test2(Test2Request request) {
        log.info("Test receive: {}", request);
        Test2 test = request2Mapper.toDomain(request);
        Test2Response response = response2Mapper.toDto(test);
        log.info("Test response: {}", response);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Test2Response> test3(String email) {
        return ResponseEntity.ok(Test2Response.builder()
                .email(email)
                .build());
    }
}
