package com.guardian.guardianbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardian.guardianbackend.models.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	public Optional<Driver> findByEmail(String email);
}
