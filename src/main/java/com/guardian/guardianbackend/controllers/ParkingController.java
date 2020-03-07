package com.guardian.guardianbackend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardian.guardianbackend.models.Booking;
import com.guardian.guardianbackend.models.Parking;
import com.guardian.guardianbackend.models.ParkingSpot;
import com.guardian.guardianbackend.models.Vehicle;
import com.guardian.guardianbackend.models.VehicleType;
import com.guardian.guardianbackend.models.ViewModels.BookingViewModel;
import com.guardian.guardianbackend.models.ViewModels.BuscaVehicleViewModel;
import com.guardian.guardianbackend.models.ViewModels.VehicleViewModel;
import com.guardian.guardianbackend.repository.BookingRepository;
import com.guardian.guardianbackend.repository.ParkingRepository;
import com.guardian.guardianbackend.repository.ParkingSpotRepository;
import com.guardian.guardianbackend.repository.VehicleRepository;
import com.guardian.guardianbackend.repository.VehicleTypeRepository;

@CrossOrigin
@RestController
@RequestMapping("api/parking")
public class ParkingController {

	@Autowired
	private ParkingRepository _parkingRepository;

	@Autowired
	private ParkingSpotRepository _parkingSpotRepository;
	@Autowired
	private BookingRepository _bookingRepository;
	@Autowired
	private VehicleRepository _vehicleRepository;
	@Autowired
	private VehicleTypeRepository _vehicleTypeRepository;

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Parking> findAll() {
		Iterable<Parking> parkings = _parkingRepository.findAll();
		return parkings;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Parking> findById(@PathVariable long id) {
		Optional<Parking> oParking = _parkingRepository.findById(id);
		if (!oParking.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(oParking.get());
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Parking register(@RequestBody @Valid Parking parking) {
		Optional<Parking> oParking = _parkingRepository.findByEmail(parking.getEmail());
		if (oParking.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Parking already exists");
		return _parkingRepository.save(parking);
	}

	@PutMapping()
	public ResponseEntity<Parking> update(@RequestBody Parking newParking) {
		Optional<Parking> oParking = _parkingRepository.findById(newParking.getID());
		if (oParking.isPresent())
			_parkingRepository.deleteById(newParking.getID());
		else
			return ResponseEntity.notFound().build();
		return ResponseEntity.accepted().body(register(newParking));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Parking> delete(@PathVariable long id) {
		Optional<Parking> oParking = _parkingRepository.findById(id);
		if (oParking.isPresent())
			_parkingRepository.deleteById(id);
		else
			return ResponseEntity.notFound().build();
		return ResponseEntity.accepted().body(oParking.get());
	}

	@PostMapping("/login")
	public ResponseEntity<Parking> login(@RequestBody ObjectNode login) {
		String email = login.get("email").asText();
		String password = login.get("password").asText();
		Optional<Parking> oParking = _parkingRepository.findByEmail(email);
		if (oParking.isPresent()) {
			if (oParking.get().getPassword().equals(password)) {
				return ResponseEntity.accepted().body(oParking.get());
			} else {
				return ResponseEntity.status(401).build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/vehicles")
	public ResponseEntity<List<BuscaVehicleViewModel>> allVehicles(@PathVariable long id) {
		Optional<Parking> oParking = _parkingRepository.findById(id);
		if (!oParking.isPresent())
			return ResponseEntity.notFound().build();

		
		// Lista de vagas por estacionamento
		List<ParkingSpot> parkingSpots = _parkingSpotRepository.findByIdParking(oParking.get().getID());

		List<Long> idsParkingsSpots = new ArrayList<Long>();

		for (ParkingSpot ps : parkingSpots) {
			idsParkingsSpots.add(ps.getId());
		}

		// Lista todos os bookings
		List<Booking> bookings = _bookingRepository.findAll();

		// Filtra bookings por parkingSpot
		List<Booking> bookingsFiltrados = new ArrayList<Booking>();

		for (Booking bk : bookings) {
			if (idsParkingsSpots.contains(bk.getIdParkingSpot())) {
				bookingsFiltrados.add(bk);
			}
		}

		// Pegar veiculos por idVehicle dos bookingsFiltrados
		List<Vehicle> vehicles = _vehicleRepository.findAll();
		List<Vehicle> vehiclesFiltrados = new ArrayList<Vehicle>();

		// Filtra por bookings
		for (Vehicle vh : vehicles) {
			for (Booking bk : bookingsFiltrados) {
				if (bk.getIdVehicle() == vh.getId() && !vehiclesFiltrados.contains(vh)) {
					vehiclesFiltrados.add(vh);
				}
			}
		}

		List<BuscaVehicleViewModel> vehiclesViewModel = new ArrayList<BuscaVehicleViewModel>();

		// for (Vehicle vh : vehiclesFiltrados) {
		// 	BuscaVehicleViewModel retornoViewModel = new BuscaVehicleViewModel();


		// 	VehicleViewModel vvw = new VehicleViewModel();
		// 	vvw.setIdVehicle(vh.getId());
		// 	vvw.setPlate(vh.getPlate());

		// 	BookingViewModel bkvw = new BookingViewModel();
		// 	for (Booking bk : bookingsFiltrados) {
		// 		if (bk.getIdVehicle() == vh.getId()) {
		// 			bkvw.setIdBooking(bk.getID());
		// 			bkvw.setCheckIn(bk.getCheckIn().toString());
		// 			bkvw.setCheckOut(bk.getCheckOut().toString());
		// 			bkvw.setVehicle(vvw);
		// 		}

		// 	}

		// 	retornoViewModel.setBooking(bkvw);
		// }

		for(ParkingSpot pk : parkingSpots){
			BuscaVehicleViewModel retornoViewModel = new BuscaVehicleViewModel();

			retornoViewModel.setIdParkingSpot(pk.getId());
			retornoViewModel.setNome(pk.getName());
			retornoViewModel.setStatus(pk.getIdStatus());

			BookingViewModel bkvw = new BookingViewModel();
			for (Booking bk : bookingsFiltrados) {
				if(bk.getIdParkingSpot() == pk.getId()){
					for (Vehicle vh : vehiclesFiltrados) {
						VehicleViewModel vvw = new VehicleViewModel();
						vvw.setIdVehicle(vh.getId());
						vvw.setPlate(vh.getPlate());
						if (bk.getIdVehicle() == vh.getId()) {
							bkvw.setIdBooking(bk.getID());
							bkvw.setCheckIn(bk.getCheckIn().toString());
							bkvw.setCheckOut(bk.getCheckOut().toString());
							bkvw.setVehicle(vvw);
						}
					}
					retornoViewModel.setBooking(bkvw);
				}
			}

			vehiclesViewModel.add(retornoViewModel);
		}



		return ResponseEntity.ok(vehiclesViewModel);
	}

}
