package com.guardian.guardianbackend.repository;

import java.util.List;
import java.util.Optional;

import com.guardian.guardianbackend.models.Price;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PriceRepository
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

    public List<Price> findAllByIdParking(long IdParking);
    public Optional<Price> findByIdParkingAndIdVehicleType(long IdParking, long idVehicleType);
    public boolean deleteByIdParkingAndIdVehicleType(long IdParking, long idVehicleType);
    public Optional<Price> findByIdParkingAndTimeInterval(long idParking, String timeInterval);
    
}