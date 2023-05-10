package com.example.transactionslocks.service;

import com.example.transactionslocks.dto.Votes;
import com.example.transactionslocks.repository.TechStarsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechStarService {

    private final TechStarsRepository starsRepository;
    private final HistoryService historyService;
    private final StreamBridge streamBridge;

    @Transactional
    @Retryable(maxAttempts = 15)
    public void addVotesToStar(Votes votes) {
        if (votes.getTechnology() != null) {
            starsRepository.findByCode(votes.getCode()).ifPresentOrElse(star -> {
                saveMessageToHistory(votes, "RECEIVED");
                star.setVotes(star.getVotes() + votes.getVotes());
                star.setTechnology(votes.getTechnology());
                starsRepository.save(star);
                log.info("{} likes add to <{} {}> for {}.", votes.getVotes(), star.getFirstName(), star.getLastName(), star.getTechnology());
            }, () -> {
                log.warn("Tech Star with internal code {} not found", votes.getCode());
                saveMessageToHistory(votes, "NONAME");
            });
        } else {
            log.error("Error during adding likes, no IDs given");
            saveMessageToHistory(votes, "CORRUPTED");
        }
    }

    private void saveMessageToHistory(Votes votes, String status) {
        historyService.saveMessageToHistory(votes, status);
    }

    public void createTaskToAddLikes(Votes votes) {
        streamBridge.send("votesProducer-out-0", votes);
        log.info("Vote message has been sent: {}", votes);
    }
}
