package com.guardian.guardianbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Price {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Price")
    private long idPrice;

    @Column(name = "id_Parking")
    private long idParking;

    @Column(name = "id_Vehicle_type")
    private long idVehicleType;

    @NotNull
    @Column(name = "time_interval")
    private String timeInterval;

    @NotNull
    private double value;

    public Price () {}

    public long getIdPrice() {
        return this.idPrice;
    }
   
    public void setIdPrice(long idPrice) {
        this.idPrice = idPrice;
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

    public String getTimeInterval() {
        return this.timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}