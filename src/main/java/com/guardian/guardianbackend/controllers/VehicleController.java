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

import com.guardian.guardianbackend.models.Vehicle;
import com.guardian.guardianbackend.repository.VehicleRepository;

@CrossOrigin
@RestController
@RequestMapping("api/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleRepository _vehicleRepository;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Vehicle>  findAll(){
		Iterable<Vehicle> vehicles = _vehicleRepository.findAll();
        return vehicles;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable long id){
		Optional<Vehicle> oVehicle = _vehicleRepository.findById(id);
		if(!oVehicle.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(oVehicle.get());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Vehicle register(@Valid @RequestBody Vehicle vehicle) {
		Optional<Vehicle> oVehicle = _vehicleRepository.findByPlate(vehicle.getPlate());
		if(oVehicle.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A Vehicle with this plate already exists");
		return _vehicleRepository.save(vehicle);
	}
	
	@PutMapping()
	public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle){
		Optional<Vehicle> oVehicle = _vehicleRepository.findById(vehicle.getId());
		if(oVehicle.isPresent())
			_vehicleRepository.deleteById(vehicle.getId());
		else
			return ResponseEntity.notFound().build();
		return ResponseEntity.accepted().body(register(vehicle));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Vehicle> delete(@PathVariable long id) {
		Optional<Vehicle> oVehicle = _vehicleRepository.findById(id);
		if(oVehicle.isPresent())
			_vehicleRepository.deleteById(id);
		else
			return ResponseEntity.notFound().build();
		return ResponseEntity.accepted().body(oVehicle.get());
	}
}
