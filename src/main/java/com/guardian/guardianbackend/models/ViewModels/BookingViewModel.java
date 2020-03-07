package com.guardian.guardianbackend.models.ViewModels;

public class BookingViewModel {
		
	private long idBooking;
	private String checkIn;
	private String checkOut;
	private VehicleViewModel vehicle;
	
	
	public BookingViewModel() {
		
	}
	
	public long getIdBooking() {
		return idBooking;
	}
	public void setIdBooking(long idBooking) {
		this.idBooking = idBooking;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public VehicleViewModel getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleViewModel vehicle) {
		this.vehicle = vehicle;
	}
	
}
