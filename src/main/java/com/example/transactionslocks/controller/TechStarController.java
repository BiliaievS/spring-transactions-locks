package com.example.transactionslocks.controller;

import com.example.transactionslocks.dto.Votes;
import com.example.transactionslocks.service.TechStarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TechStarController {

    private final TechStarService service;

    @PostMapping("/addvotes")
    public ResponseEntity<String> updateTechStarVotes(@RequestBody Votes votes) {
        try {
            service.addVotesToStar(votes);
            return new ResponseEntity<>("Votes successfully added.", HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.warn("Exception in controller:", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
