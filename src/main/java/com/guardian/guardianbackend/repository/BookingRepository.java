package com.guardian.guardianbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardian.guardianbackend.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	Optional<Booking> findByIdVehicleAndIdParkingSpot(long idVehicle, long idParkingSpot);
	
	List<Booking> findByIdVehicle(long idVehicle);
}