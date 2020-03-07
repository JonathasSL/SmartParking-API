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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.guardian.guardianbackend.models.Status;
import com.guardian.guardianbackend.repository.StatusRepository;;

@CrossOrigin
@RestController
@RequestMapping("api/status")
public class StatusController {

	@Autowired
	private StatusRepository _statusRepository;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Status> findAll() {
		Iterable<Status> drivers = _statusRepository.findAll();
		return drivers;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Status> findById(@PathVariable long id) {
		Optional<Status> oStatus = _statusRepository.findById(id);
		if (!oStatus.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(oStatus.get());
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Status register(@Valid @RequestBody Status status) {
		Optional<Status> oStatus = _statusRepository.findById(status.getID());
		if (oStatus.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This driver already exists");
		return _statusRepository.save(status);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Status> delete(@PathVariable long id) {
		Optional<Status> oStatus = _statusRepository.findById(id);
		if (oStatus.isPresent())
			_statusRepository.deleteById(id);
		else
			return ResponseEntity.notFound().build();
		return ResponseEntity.accepted().body(oStatus.get());
	}
	
	
}
