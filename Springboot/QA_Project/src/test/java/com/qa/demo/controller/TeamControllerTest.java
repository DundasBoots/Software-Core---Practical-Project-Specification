package com.qa.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.domain.dao.Team;
import com.qa.demo.domain.dto.TeamDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:f1-schema.sql", "classpath:f1-data.sql"})
public class TeamControllerTest {
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	private TeamDto mapToDTO(Team team) {
        return this.mapper.map(team, TeamDto.class);
    }
	
	@Autowired
	private ObjectMapper jsonifier;

	private final Long Test_Id = 1L;
	private final Team Test_Team = new Team(1L, "Red Bull", "Honda", 4, "Christian Horner", "1st");
	
	@Test
	public void createTest() throws Exception {
		RequestBuilder request = post("/team/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(Test_Team));
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(this.jsonifier.writeValueAsString(this.mapToDTO(Test_Team)));
		this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	public void testReadAll() throws Exception {
		final String resultString = this.mock
				.perform(request(HttpMethod.GET, "/team/readAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<Team> results = Arrays.asList(jsonifier.readValue(resultString, Team[].class));
		assertThat(results).contains(Test_Team).hasSize(1);
}
	@Test
	public void testDelete() throws Exception {
		this.mock.perform(delete("/team/delete/"+Test_Id)).andExpect(status().isNoContent());
	}
}