package com.guardian.guardianbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardian.guardianbackend.models.VehicleType;

public interface VehicleTypeRepository  extends JpaRepository<VehicleType, Long> {

}
