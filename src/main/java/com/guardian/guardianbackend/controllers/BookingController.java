package com.guardian.guardianbackend.controllers;

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

import com.guardian.guardianbackend.models.Booking;
import com.guardian.guardianbackend.repository.BookingRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/booking")
public class BookingController{

    @Autowired
    private BookingRepository _bookingRepository;

    @GetMapping(produces = "application/json")
    public @ResponseBody Iterable<Booking> findAll(){
        Iterable<Booking> bookings = _bookingRepository.findAll();
        return bookings;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable long id){
    	Optional<Booking> booking = _bookingRepository.findById(id);
    	if(!booking.isPresent())
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(booking.get());
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Booking register(@Valid @RequestBody Booking booking){
    	Optional<Booking> oBooking = _bookingRepository.findByIdVehicleAndIdParkingSpot(booking.getIdVehicle(), booking.getIdParkingSpot());
    	if(oBooking.isPresent())
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A Booking with this idVehicle and idParkingSpot already exists");
        return _bookingRepository.save(booking);
    }
    
    @PutMapping()
    public ResponseEntity<Booking> update(@RequestBody Booking booking){
    	Optional<Booking> oBooking = _bookingRepository.findByIdVehicleAndIdParkingSpot(booking.getIdVehicle(), booking.getIdParkingSpot());
    	if(oBooking.isPresent()) {
    		_bookingRepository.delete(_bookingRepository.findByIdVehicleAndIdParkingSpot(booking.getIdVehicle(), booking.getIdParkingSpot()).get());
    	}else
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.accepted().body(register(booking));
    }

    //TODO : Delete mapping
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Booking> delete(@PathVariable long id){
    //     Optional<Booking> oBooking = _bookingRepository.findByIdVehicleAndIdParkingSpot(booking.getIdVehicle(), booking.getIdParkingSpot());
    //     if(oBooking.isPresent())
    //     	_bookingRepository.findByIdVehicleAndIdParkingSpot(booking.getIdVehicle(), booking.getIdParkingSpot());
    //     else
    //     	return ResponseEntity.notFound().build();
    //     return ResponseEntity.accepted().body(oBooking.get());
    // }
}