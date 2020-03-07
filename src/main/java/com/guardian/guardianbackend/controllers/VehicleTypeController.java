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

import com.guardian.guardianbackend.models.VehicleType;
import com.guardian.guardianbackend.repository.VehicleTypeRepository;

@CrossOrigin
@RestController
@RequestMapping("api/vehicletype")
public class VehicleTypeController {
	
	@Autowired
	private VehicleTypeRepository _vehicleTypeRepository;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<VehicleType> findAll() {
		Iterable<VehicleType> drivers = _vehicleTypeRepository.findAll();
		return drivers;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<VehicleType> findById(@PathVariable long id) {
		Optional<VehicleType> oVehicleType = _vehicleTypeRepository.findById(id);
		if (!oVehicleType.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(oVehicleType.get());
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public VehicleType register(@Valid @RequestBody VehicleType VehicleType) {
		Optional<VehicleType> oVehicleType = _vehicleTypeRepository.findById(VehicleType.getID());
		if (oVehicleType.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This driver already exists");
		return _vehicleTypeRepository.save(VehicleType);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<VehicleType> delete(@PathVariable long id) {
		Optional<VehicleType> oVehicleType = _vehicleTypeRepository.findById(id);
		if (oVehicleType.isPresent())
			_vehicleTypeRepository.deleteById(id);
		else
			return ResponseEntity.notFound().build();
		return ResponseEntity.accepted().body(oVehicleType.get());
	}
	
	
}
