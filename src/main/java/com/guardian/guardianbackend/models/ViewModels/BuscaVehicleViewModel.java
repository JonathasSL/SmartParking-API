package com.guardian.guardianbackend.models.ViewModels;


public class BuscaVehicleViewModel {
	
	private long idParkingSpot;
	private String nome;
	private long status;
	private BookingViewModel booking;
	
	
	public BuscaVehicleViewModel() {
		
	}


	public long getIdParkingSpot() {
		return idParkingSpot;
	}


	public void setIdParkingSpot(long idParkingSpot) {
		this.idParkingSpot = idParkingSpot;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public long getStatus() {
		return status;
	}


	public void setStatus(long status) {
		this.status = status;
	}


	public BookingViewModel getBooking() {
		return booking;
	}


	public void setBooking(BookingViewModel booking) {
		this.booking = booking;
	}
	
}
