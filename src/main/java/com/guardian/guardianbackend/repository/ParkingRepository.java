package com.guardian.guardianbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardian.guardianbackend.models.Parking;


public interface ParkingRepository extends JpaRepository<Parking, Long> {
	
	Optional<Parking> findByEmail(String email);
}
