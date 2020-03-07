package com.guardian.guardianbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Parking_Spot")
    private long id;

    private String name;

    @Column(name = "id_Parking")
    private long idParking;

    @Column(name = "id_Vehicle_Type")
    private long idVehicleType;

    @Column(name = "id_status")
    private long idStatus;

    public ParkingSpot () {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public long getIdParking() {
        return this.idParking;
    }

    public void setIdParking(long idParking) {
        this.idParking = idParking;
    }

    public long getIdVehicleType() {
        return this.idVehicleType;
    }

    public void setIdVehicleType(long idVehicleType) {
        this.idVehicleType = idVehicleType;
    }

    public long getIdStatus() {
        return this.idStatus;
    }

    public void setIdStatus(long idStatus) {
        this.idStatus = idStatus;
    }


    
}