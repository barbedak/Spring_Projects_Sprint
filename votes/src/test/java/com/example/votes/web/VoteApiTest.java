package com.example.votes.web;

import com.example.votes.app.domain.VoteValue;
import com.example.votes.web.dto.SaveVoteRequest;
import com.example.votes.web.dto.SaveVoteResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class VoteApiTest {

    private static final Random random = new Random();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
        UUID userId = UUID.randomUUID();

        SaveVoteRequest[] requests = new SaveVoteRequest[]{
                //initial vote request
                SaveVoteRequest.builder()
                        .userId(userId)
                        .voteValue(randomVoteValue())
                        .build(),

                //change vote request
                SaveVoteRequest.builder()
                        .userId(userId)
                        .voteValue(randomVoteValue())
                        .build()
        };

        SaveVoteResponse[] responses = new SaveVoteResponse[]{
                //initial vote response
                SaveVoteResponse.builder()
                        .isSaved(true)
                        .build(),

                //change vote response
                SaveVoteResponse.builder()
                        .isSaved(false)
                        .build()
        };

        for (int i = 0; i < requests.length; i++) {
            SaveVoteRequest request = requests[i];
            SaveVoteResponse response = responses[i];

            mockMvc.perform(post("/votes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJson(request)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(asJson(response), true));

        }
    }

    private static VoteValue randomVoteValue() {
        VoteValue[] voteValues = VoteValue.values();
        return voteValues[random.nextInt(voteValues.length)];
    }

    private String asJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}