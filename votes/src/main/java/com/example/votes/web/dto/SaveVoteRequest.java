package com.example.votes.web.dto;

import com.example.votes.app.domain.VoteValue;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveVoteRequest {
    private UUID userId;

    private VoteValue voteValue;
}
