package com.guardian.guardianbackend.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardian.guardianbackend.models.Price;
import com.guardian.guardianbackend.repository.PriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * PriceController
 */
@CrossOrigin
@RestController
@RequestMapping("api/price")
public class PriceController {

    @Autowired
    PriceRepository _priceRepository;

    @GetMapping()
    public @ResponseBody Iterable<Price> findAll() {
        Iterable<Price> prices = _priceRepository.findAll();
        return prices;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value = "/{idParking}", produces = "application/json")
    public @ResponseBody Iterable<Price> findAllbyParking(@PathVariable long idParking){
        Iterable<Price> prices = _priceRepository.findAllByIdParking(idParking);
        return prices;
    }

    @PostMapping()
    public ResponseEntity<Price> register(@RequestBody @Valid Price price) {
        Optional<Price> oPrice = _priceRepository.findByIdParkingAndIdVehicleType(price.getIdParking(), price.getIdVehicleType());
        if(!oPrice.isPresent())
            // if (oPrice.get().getTimeInterval().equals(_priceRepository.findByIdParkingAndTimeInterval(price.getIdParking(), price.getTimeInterval()).get().getTimeInterval())) {
            //     throw new ResponseStatusException(HttpStatus.CONFLICT,"A price with this time interval already exists");
            // } else
                _priceRepository.save(price);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(price);
    }

    @PutMapping()
    public ResponseEntity<Price> update(@RequestBody @Valid Price price) {
        Optional<Price> oPrice = _priceRepository.findByIdParkingAndIdVehicleType(price.getIdParking(), price.getIdVehicleType());

        if(oPrice.isPresent()){
            _priceRepository.save(price);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(price);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The price with this idParking and idVehicleType was not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Price> delete(/*@RequestBody ObjectNode ONPrice*/ @PathVariable long id){
        // Price price = new Price();
        // price.setIdParking(ONPrice.get("idParking").asLong());
        // price.setIdVehicleType(ONPrice.get("idVehicleType").asLong());
        // price.setTimeInterval(ONPrice.get("timeInterval").asText());  
        // price.setValue(ONPrice.get("value").asDouble());

        Optional<Price> oPrice = _priceRepository.findById(id);
        if(oPrice.isPresent()){
            /* price = oPrice.get();
            if(*/_priceRepository.deleteById(id);/*deleteByIdParkingAndIdVehicleType(price.getIdParking(), price.getIdVehicleType())) {*/
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(oPrice.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This price was not found in the DataBase");
        }

    }
    
    
}