package com.qa.demo.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.domain.dao.Driver;
import com.qa.demo.domain.dao.Team;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:f1-schema.sql", "classpath:f1-data.sql"})
public class TeamControllerTest {
	@Autowired
	private MockMvc mock;
	
//	@Autowired
//	private ModelMapper mapper;
//	
//	private DriverDto mapToDTO(Driver driver) {
//        return this.mapper.map(driver, DriverDto.class);
//    }
	
	@Autowired
	private ObjectMapper jsonifier;

	private final Long Test_Id = 1L;
	private final Driver Test_Driver = new Driver(1L, "Ser", "Per", 32, "Mex", 3, "3rd", new Team(1L, "Red Bull", "Honda", 4, "Christian Horner", "1st"));
	
	@Test
	public void createTest() throws Exception {
		this.mock
			.perform(post("/driver/create")
					.accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(this.jsonifier.writeValueAsString(Test_Driver)))
	            .andExpect(status().isCreated())
	            .andExpect((ResultMatcher) content().json(this.jsonifier.writeValueAsString(Test_Driver)));
	}
}