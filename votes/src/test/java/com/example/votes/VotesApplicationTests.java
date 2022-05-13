package com.example.votes;

import com.example.votes.app.domain.Vote;
import com.example.votes.app.domain.VoteValue;
import com.example.votes.app.repository.VoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class VotesApplicationTests {

	@Test
	void contextLoads() {
	}
}

@SpringBootTest()
@AutoConfigureMockMvc
class HttpRequestTest{

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private VoteRepository repository;

	private Vote vote = new Vote();

	@Test
	public void shouldReturnTrueOnFirstAddVote() throws Exception {
		vote.setValue(VoteValue.valueOf("Y"));
		vote.setUserId(UUID.fromString("020bba59-eae7-434e-a0d4-f74ac84467fb"));

		mockMvc.perform(post("/votes")
				.content(objectMapper.writeValueAsString(vote))
				.contentType(MediaType.APPLICATION_JSON)
		)
				.andExpect(jsonPath("$.saved").isBoolean())
				.andExpect(jsonPath("$.saved").value("true"));
	}

	@Test
	public void shouldReturnFalseOnSecondAddVote() throws Exception {
		vote.setValue(VoteValue.valueOf("Y"));
		vote.setUserId(UUID.fromString("020bba59-eae7-434e-a0d4-f74ac84467fb"));
		repository.save(vote);

		mockMvc.perform(post("/votes")
						.content(objectMapper.writeValueAsString(vote))
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(jsonPath("$.saved").isBoolean())
				.andExpect(jsonPath("$.saved").value("false"));
	}
}