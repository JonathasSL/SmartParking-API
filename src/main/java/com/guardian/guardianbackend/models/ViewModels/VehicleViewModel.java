package com.guardian.guardianbackend.models.ViewModels;

public class VehicleViewModel {

	private long idVehicle;
	private String plate;
	
	
	public VehicleViewModel() {
		
	}
	
	public long getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(long idVehicle) {
		this.idVehicle = idVehicle;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
}
