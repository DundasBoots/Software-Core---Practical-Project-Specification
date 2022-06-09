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

import com.qa.demo.domain.dao.Driver;
import com.qa.demo.domain.dto.DriverDto;
import com.qa.demo.service.DriverService;

@RestController
@CrossOrigin
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService service;

	public DriverController(DriverService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<DriverDto> createDriver(@RequestBody Driver driver) {
		return new ResponseEntity<>(this.service.create(driver), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<DriverDto>> readAll() {
		return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
	}

	@GetMapping("/readById/{id}")
	public ResponseEntity<DriverDto> readById(@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(this.service.readById(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<DriverDto> updateDriver(@PathVariable Long id, @RequestBody Driver driver) throws Exception {
		return new ResponseEntity<>(this.service.updateDriver(id, driver), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteDriver(@PathVariable Long id) throws Exception {
		return this.service.deleteDriver(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
