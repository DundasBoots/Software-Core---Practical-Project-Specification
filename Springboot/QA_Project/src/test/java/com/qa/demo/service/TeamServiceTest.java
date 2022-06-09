package com.qa.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.demo.domain.dao.Team;
import com.qa.demo.domain.dto.TeamDto;
import com.qa.demo.exception.TeamException;
import com.qa.demo.repo.TeamRepo;

@SpringBootTest
@ActiveProfiles("test")
public class TeamServiceTest {

	@Autowired
	private TeamService service;

	@MockBean
	private TeamRepo repo;
	List<Team> expectedTeams;
	List<TeamDto> expectedTeamsDto;
	Team expectedTeam;
	TeamDto expectedTeamDto;

	@BeforeEach
	void setup() {
		expectedTeamsDto = List.of(new TeamDto());
		expectedTeams = List.of(new Team());
		expectedTeam = new Team();
		expectedTeamDto = new TeamDto();
	}

	@Test
	void readAllTest() {

		Mockito.when(this.repo.findAll()).thenReturn(expectedTeams);
		assertThat(this.service.readAll()).isEqualTo(expectedTeamsDto);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void readById() throws TeamException {
	expectedTeam.setId(1L);
	expectedTeamDto.setId(1L);
	
	Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(expectedTeam));
	assertThat(this.service.readById(1L)).isEqualTo(expectedTeamDto);
	Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}

	@Test
	void createTest() {
		Mockito.when(this.repo.save(new Team())).thenReturn(expectedTeam);
		assertThat(this.service.create(new Team())).isEqualTo(expectedTeamDto);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Team());
	}
	@Test
	void updatedDtoTest() throws TeamException {

		Team created = new Team();
		created.setTeam_name("Red Bull");
		created.setEngine_manufacturer("Honda");
		created.setConstructors_championships(4);
		created.setTeam_principal("Christian Horner");
		created.setBest_championship_position("1st");

		Team updated = new Team();
		updated.setTeam_name("Red Bull");
		updated.setEngine_manufacturer("Honda");
		updated.setConstructors_championships(5);
		updated.setTeam_principal("Christian Horner");
		updated.setBest_championship_position("1st(new text for test)");

		TeamDto updatedDto = new TeamDto();
		updatedDto.setTeam_name("Red Bull");
		updatedDto.setEngine_manufacturer("Honda");
		updatedDto.setConstructors_championships(5);
		updatedDto.setTeam_principal("Christian Horner");
		updatedDto.setBest_championship_position("1st(new text for test)");
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(created));
		Mockito.when(this.repo.save(created)).thenReturn(created);

		assertThat(this.service.updateTeam(1L, updated)).isEqualTo(updatedDto);

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(created);
	}
	
	@Test
	void deleteTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
        assertThat(this.service.deleteTeam(1L)).isTrue();
        Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
}
