package com.guardian.guardianbackend.repository;

import java.util.List;

import com.guardian.guardianbackend.models.ParkingSpot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long>{
    public List<ParkingSpot> findByIdParking(long idParking);
    public List<ParkingSpot> findByIdStatus(long idStatus);
    public List<ParkingSpot> findByNameAndIdParking(String name, long idParking);
}