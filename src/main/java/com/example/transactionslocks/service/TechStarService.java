package com.example.transactionslocks.service;

import com.example.transactionslocks.dto.Votes;
import com.example.transactionslocks.repository.TechStarsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TechStarService {

    private final TechStarsRepository starsRepository;
    private final HistoryService historyService;

    public void addVotesToStar(Votes votes) {
        if (votes.getTechnology() != null) {
            starsRepository.findByTechnology(votes.getTechnology()).ifPresentOrElse(star -> {
                saveMessageToHistory(votes, "RECEIVED");
                star.setVotes(star.getVotes() + votes.getVotes());
                starsRepository.save(star);
                log.info("{} likes add to <{} {}>", votes.getVotes(), star.getFirstName(), star.getLastName());
            }, () -> {
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
}
