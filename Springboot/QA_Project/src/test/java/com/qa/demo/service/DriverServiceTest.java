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

import com.qa.demo.domain.dao.Driver;
import com.qa.demo.domain.dto.DriverDto;
import com.qa.demo.exception.DriverException;
import com.qa.demo.repo.DriverRepo;

@SpringBootTest
@ActiveProfiles("test")
public class DriverServiceTest {

	@Autowired
	private DriverService service;

	@MockBean
	private DriverRepo repo;
	List<Driver> expectedDrivers;
	List<DriverDto> expectedDriversDto;
	Driver expectedDriver;
	DriverDto expectedDriverDto;

	@BeforeEach
	void setup() {
		expectedDriversDto = List.of(new DriverDto());
		expectedDrivers = List.of(new Driver());
		expectedDriver = new Driver();
		expectedDriverDto = new DriverDto();
	}

	@Test
	void readAllTest() {

		Mockito.when(this.repo.findAll()).thenReturn(expectedDrivers);
		assertThat(this.service.readAll()).isEqualTo(expectedDriversDto);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void readById() throws DriverException {
	expectedDriver.setId(1L);
	expectedDriverDto.setId(1L);
	
	Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(expectedDriver));
	assertThat(this.service.readById(1L)).isEqualTo(expectedDriverDto);
	Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}

	@Test
	void createTest() {
		Mockito.when(this.repo.save(new Driver())).thenReturn(expectedDriver);
		assertThat(this.service.create(new Driver())).isEqualTo(expectedDriverDto);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Driver());
	}
	@Test
	void updatedDtoTest() throws DriverException {

		Driver created = new Driver();
		created.setFirst_name("Sergio");
		created.setSurname("Perez");
		created.setAge(32);
		created.setNationality("Mexico");
		created.setRace_wins(3);
		created.setBest_championship_position("3rd");

		Driver updated = new Driver();
		updated.setFirst_name("updated");
		updated.setSurname("Perez");
		updated.setAge(32);
		updated.setNationality("Mexico");
		updated.setRace_wins(3);
		updated.setBest_championship_position("3rd");

		DriverDto updatedDto = new DriverDto();
		updatedDto.setFirst_name("updated");
		updatedDto.setSurname("Perez");
		updatedDto.setAge(32);
		updatedDto.setNationality("Mexico");
		updatedDto.setRace_wins(3);
		updatedDto.setBest_championship_position("3rd");
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(created));
		Mockito.when(this.repo.save(created)).thenReturn(created);

		assertThat(this.service.updateDriver(1L, updated)).isEqualTo(updatedDto);

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(created);
	}
	
	@Test
	void deleteTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
        assertThat(this.service.deleteDriver(1L)).isTrue();
        Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
}
