package com.example.transactionslocks.controller;

import com.example.transactionslocks.dto.Likes;
import com.example.transactionslocks.service.SpeakerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SpeakerController {

    private SpeakerService service;

    @PostMapping("/adlikes")
    public ResponseEntity<String> updateSpeaker(@RequestBody Likes likes) {
        try {
            service.addLikesToSpeaker(likes);
            return new ResponseEntity<>("Likes successfully added.", HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.warn("Exception in controller:", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
