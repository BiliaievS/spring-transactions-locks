package com.example.transactionslocks.repository;

import com.example.transactionslocks.entity.TechStarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechStarsRepository extends JpaRepository<TechStarEntity, Long> {

    Optional<TechStarEntity> findByTechnology(String technology);

    Optional<TechStarEntity> findByCode(String code);
}
