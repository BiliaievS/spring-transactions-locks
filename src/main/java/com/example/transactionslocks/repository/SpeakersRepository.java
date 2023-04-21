package com.example.transactionslocks.repository;

import com.example.transactionslocks.entity.SpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakersRepository extends JpaRepository<SpeakerEntity, Long> {

    Optional<SpeakerEntity> findByTalkName(String talkName);
}
