package com.example.transactionlogdemo.adapter.controller.test;

import com.example.transactionlogdemo.adapter.api.test.TestApi;
import com.example.transactionlogdemo.application.dto.request.test.TestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController implements TestApi {
    @Override
    public void test(TestRequest request) {
        log.info("Test receive: {}", request);
    }
}
