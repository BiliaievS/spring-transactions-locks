package com.example.transactionslocks.service;

import com.example.transactionslocks.dto.Votes;
import com.example.transactionslocks.entity.HistoryEntity;
import com.example.transactionslocks.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository repository;

    public void saveMessageToHistory(Votes votes, String status) {
        try {
            repository.save(HistoryEntity.builder()
                    .technology(votes.getTechnology())
                    .votes(votes.getVotes())
                    .status(status)
                    .build());
        } catch (RuntimeException e) {
            log.error("Couldn't save message to history.", e);
        }
    }

}
