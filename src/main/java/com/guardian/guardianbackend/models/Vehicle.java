package com.guardian.guardianbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vehicle")
	private long id;
	
	@NotNull
	@Column(name = "id_driver")
	private long idDriver;
	
	@NotNull
	@Column(name = "id_vehicle_type")
	private long idVehicleType;
	
	@NotBlank
	private String plate;
		
	@NotBlank
	private String make; //Fabricant, maker
	
	@NotBlank
	private String model;
	
	private String picture;
	
	private String color;
	
	public Vehicle() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdDriver() {
		return idDriver;
	}

	public void setIdDriver(long idDriver) {
		this.idDriver = idDriver;
	}

	public long getIdVehicleType() {
		return idVehicleType;
	}

	public void setIdVehicleType(long idVehicleType) {
		this.idVehicleType = idVehicleType;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
}
