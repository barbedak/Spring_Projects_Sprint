package com.example.votes.web;

import com.example.votes.app.domain.Vote;
import com.example.votes.app.service.VoteService;
import com.example.votes.web.dto.SaveVoteRequest;
import com.example.votes.web.dto.SaveVoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class VoteApi {

    private final VoteService service;

    @PostMapping(path = "/votes")
    public SaveVoteResponse save(@RequestBody SaveVoteRequest request){
        Vote vote = new Vote();
        vote.setUserId(request.getUserId());
        vote.setValue(request.getVoteValue());

        boolean isSaved = service.save(vote);

        SaveVoteResponse response = new SaveVoteResponse();
        response.setSaved(isSaved);

        return response;
    }
}

