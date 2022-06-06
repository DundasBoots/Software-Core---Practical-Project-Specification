package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.dao.Driver;
import com.qa.demo.dto.DriverDto;
import com.qa.demo.exception.DriverException;
import com.qa.demo.repo.DriverRepo;

@Service
public class DriverService {
	private DriverRepo repo;
	private ModelMapper mapper;

	@Autowired
	public DriverService(DriverRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	private DriverDto mapToDto(Driver driver) {
		return this.mapper.map(driver, DriverDto.class);
	}

	public DriverDto create(Driver driver) {
		return this.mapToDto(this.repo.save(driver));
	}

	public List<DriverDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public DriverDto readById(Long id) throws DriverException {
		return this.mapToDto(this.repo.findById(id).orElseThrow(DriverException::new));
	}

	public DriverDto updateDriver(Long id, Driver driver) throws DriverException {
		Driver update = this.repo.findById(id).orElseThrow(DriverException::new);

		update.setFirst_name(driver.getFirst_name());
		update.setSurname(driver.getSurname());
		update.setAge(driver.getAge());
		update.setNationality(driver.getNationality());
		update.setRace_wins(driver.getRace_wins());
		update.setBest_championship_position(driver.getBest_championship_position());

		return this.mapToDto(this.repo.save(update));

	}

	public boolean deleteDriver(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);

	}

}
