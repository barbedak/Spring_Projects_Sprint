package com.example.votes.app.repository;

import com.example.votes.app.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    boolean existsByUserId(UUID userId);
}
