package com.example.transactionslocks.service;

import com.example.transactionslocks.dto.Votes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProcessor {

    private final TechStarService techStarService;

    public void processOnMessage(Votes votes){
        techStarService.addVotesToStar(votes);
    }
}
