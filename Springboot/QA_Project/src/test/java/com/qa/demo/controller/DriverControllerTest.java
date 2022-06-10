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
import com.qa.demo.domain.dao.Driver;
import com.qa.demo.domain.dao.Team;
import com.qa.demo.domain.dto.DriverDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:f1-schema.sql", "classpath:f1-data.sql" })
public class DriverControllerTest {
	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper mapper;
	
	private DriverDto mapToDTO(Driver driver) {
        return this.mapper.map(driver, DriverDto.class);
    }

	@Autowired
	private ObjectMapper jsonifier;

	private final Long testId = 1L;
	private final Driver testDriver = new Driver(1L, "Sergio", "Perez", 32, "Mexico", 3, "3rd", new Team(1L, "Red Bull", "Honda", 4, "Christian Horner", "1st"));
	private final Driver testDriver1 = new Driver(1L, "Sergio", "Perez", 32, "Mexico", 3, "3rd", new Team());

	@Test
	public void createTest() throws Exception {
		RequestBuilder request = post("/driver/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testDriver));
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(this.jsonifier.writeValueAsString(this.mapToDTO(testDriver)));
		this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	public void testReadAll() throws Exception {
		final String resultString = this.mock
				.perform(request(HttpMethod.GET, "/driver/readAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<Driver> results = Arrays.asList(jsonifier.readValue(resultString, Driver[].class));
		assertThat(results).contains(testDriver1).hasSize(1);
}
	@Test
	public void testDelete() throws Exception {
		this.mock.perform(delete("/driver/delete/"+testId)).andExpect(status().isNoContent());
	}
}