package com.guardian.guardianbackend.controllers;

import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardian.guardianbackend.models.Driver;
import com.guardian.guardianbackend.repository.DriverRepository;

@CrossOrigin
@RestController
@RequestMapping("api/driver")
public class DriverController {

	@Autowired
	private DriverRepository _driverRepository;

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Driver> findAll() {
		Iterable<Driver> drivers = _driverRepository.findAll();
		return drivers;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Driver> findById(@PathVariable long id) {
		Optional<Driver> oDriver = _driverRepository.findById(id);
		if (!oDriver.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(oDriver.get());
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Driver register(@Valid @RequestBody Driver driver) {
		Optional<Driver> oDriver = _driverRepository.findByEmail(driver.getEmail());
		if (oDriver.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This driver already exists");
		return _driverRepository.save(driver);
	}

	@PutMapping()
	public ResponseEntity<Driver> update(@RequestBody Driver newDriver) {
		Optional<Driver> oDriver = _driverRepository.findById(newDriver.getID());
		if (oDriver.isPresent()){
			_driverRepository.save(newDriver);
			return ResponseEntity.accepted().body(newDriver);
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Driver> delete(@PathVariable long id) {
		Optional<Driver> oDriver = _driverRepository.findById(id);
		if (oDriver.isPresent())
			_driverRepository.deleteById(id);
		else
			return ResponseEntity.notFound().build();
		return ResponseEntity.accepted().body(oDriver.get());
	}

	@PostMapping("/login")
	public ResponseEntity<Driver> login(@RequestBody ObjectNode login) {
		String email = login.get("email").asText();
		String password = login.get("password").asText();
		Optional<Driver> oDriver = _driverRepository.findByEmail(email);

		if (oDriver.isPresent()) {
			if (oDriver.get().getPassword().equals(password)) {
				return ResponseEntity.accepted().body(oDriver.get());
			} else {
				return ResponseEntity.status(401).build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
