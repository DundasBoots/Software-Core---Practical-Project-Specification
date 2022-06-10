package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.domain.dao.Team;
import com.qa.demo.domain.dto.TeamDto;
import com.qa.demo.exception.TeamException;
import com.qa.demo.repo.TeamRepo;

@Service
public class TeamService {
	private TeamRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TeamService(TeamRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	private TeamDto mapToDto(Team team) {
		return this.mapper.map(team, TeamDto.class);
	}

	public TeamDto create(Team team) {
		return this.mapToDto(this.repo.save(team));
	}

	public List<TeamDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public TeamDto readById(Long id) throws TeamException {
		return this.mapToDto(this.repo.findById(id).orElseThrow(TeamException::new));
	}

	public TeamDto updateTeam(Long id, Team team) throws TeamException {
		Team update = this.repo.findById(id).orElseThrow(TeamException::new);

		update.setTeam_name(team.getTeam_name());
		update.setEngine_manufacturer(team.getEngine_manufacturer());
		update.setConstructors_championships(team.getConstructors_championships());
		update.setTeam_principal(team.getTeam_principal());
		update.setBest_championship_position(team.getBest_championship_position());

		return this.mapToDto(this.repo.save(update));

	}

	public boolean deleteTeam(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);

	}
}
