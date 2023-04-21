package com.example.transactionslocks.service;

import com.example.transactionslocks.dto.Likes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpeakerService {

    public void addLikesToSpeaker(Likes likes) {
        if (likes.getTalkName() != null) {
//            speakersRepository.findByTalkName(likes.getTalkName()).ifPresentOrElse(speaker -> {
//                saveMessageToHistory(likes, "RECEIVED");
//                speaker.setLikes(speaker.getLikes() + likes.getLikes());
//                speakersRepository.save(speaker);
//                log.info("{} likes added to {}", likes.getLikes(), speaker.getFirstName() + " " + speaker.getLastName());
//            }, () -> {
            log.warn("Speaker with talk {} not found", likes.getTalkName());
//                saveMessageToHistory(likes, "ORPHANED");
//            });
        } else {
            log.error("Error during adding likes, no IDs given");
//            saveMessageToHistory(likes, "CORRUPTED");
        }
    }
}
