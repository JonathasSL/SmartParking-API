package com.guardian.guardianbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardian.guardianbackend.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>  {
	
	Optional<Vehicle> findByPlate(String plate);
}
