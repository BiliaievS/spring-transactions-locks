package com.example.transactionslocks.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping
    public String index() {
        return "Service up and running!";
    }

    @GetMapping("/test")
    public ResponseEntity<String> testTransaction() {
        log.warn("Thread {} started", Thread.currentThread().getId());
        log.warn("Thread {} finished the work", Thread.currentThread().getId());
        return new ResponseEntity<>("Test passed!", HttpStatus.OK);
    }

}
