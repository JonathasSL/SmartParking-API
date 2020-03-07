package com.guardian.guardianbackend.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_booking")
	private long id;

	@NotBlank
	@Column(name = "id_vehicle")
	private long idVehicle;
	
	@NotBlank
	@Column(name = "id_parking_spot")
	private long idParkingSpot;
	
	@NotBlank
	@Column(name = "check_in")
	private Date checkIn;

	@Column(name = "check_out")
	private Date checkOut;
	
	@Min(0)
	private double price;

	public Booking() {}

	public long getID() {
		return id;
	}

	public void setID(long iD) {
		id = iD;
	}
	
	public long getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(long idVehicle) {
		this.idVehicle = idVehicle;
	}

	public long getIdParkingSpot() {
		return idParkingSpot;
	}

	public void setIdParkingSpot(long idParkingSpot) {
		this.idParkingSpot = idParkingSpot;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
		
}
