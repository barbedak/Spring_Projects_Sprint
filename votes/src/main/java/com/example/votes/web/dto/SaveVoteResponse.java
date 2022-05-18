package com.example.votes.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveVoteResponse {
    public SaveVoteResponse(boolean isSaved) {
        this.isSaved = isSaved;
    }

    private boolean isSaved;
}
