package com.example.votes.app.service;

import com.example.votes.app.domain.Vote;
import com.example.votes.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VoteService {

    private final VoteRepository repository;

    public boolean save(Vote vote){
        if (repository.existsByUserId(vote.getUserId())) {
            return false;
        }

        repository.save(vote);
        return true;
    }
}
