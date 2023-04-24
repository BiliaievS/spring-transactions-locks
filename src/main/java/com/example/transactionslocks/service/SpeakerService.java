package com.example.transactionslocks.service;

import com.example.transactionslocks.dto.Likes;
import com.example.transactionslocks.repository.TechStarsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpeakerService {

    private final TechStarsRepository starsRepository;
    private final HistoryService historyService;

    public void addLikesToSpeaker(Likes likes) {
        if (likes.getTechnology() != null) {
            starsRepository.findByTechnology(likes.getTechnology()).ifPresentOrElse(star -> {
                saveMessageToHistory(likes, "RECEIVED");
                star.setLikes(star.getLikes() + likes.getLikes());
                starsRepository.save(star);
                log.info("{} likes add to <{} {}>", likes.getLikes(), star.getFirstName(), star.getLastName());
            }, () -> {
                log.warn("Speaker with technology {} not found", likes.getTechnology());
                saveMessageToHistory(likes, "NONAME");
            });
        } else {
            log.error("Error during adding likes, no IDs given");
            saveMessageToHistory(likes, "CORRUPTED");
        }
    }

    private void saveMessageToHistory(Likes likes, String status) {
        historyService.saveMessageToHistory(likes, status);
    }
}
