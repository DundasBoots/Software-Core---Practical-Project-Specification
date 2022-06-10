package com.qa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.domain.dao.Team;
import com.qa.demo.domain.dto.TeamDto;
import com.qa.demo.service.TeamService;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {
	private TeamService service;

	@Autowired
	public TeamController(TeamService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<TeamDto> createTeam(@RequestBody Team team) {
		return new ResponseEntity<>(this.service.create(team), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<TeamDto>> readAll() {
		return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
	}

	@GetMapping("/readById/{id}")
	public ResponseEntity<TeamDto> readById(@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(this.service.readById(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TeamDto> updateTeam(@PathVariable Long id, @RequestBody Team team) throws Exception {
		return new ResponseEntity<>(this.service.updateTeam(id, team), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteTeam(@PathVariable Long id) throws Exception {
		return this.service.deleteTeam(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

