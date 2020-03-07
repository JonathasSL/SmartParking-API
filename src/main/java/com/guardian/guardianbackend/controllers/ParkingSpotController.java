package com.guardian.guardianbackend.controllers;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guardian.guardianbackend.models.ParkingSpot;
import com.guardian.guardianbackend.repository.ParkingSpotRepository;

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

@CrossOrigin
@RestController
@RequestMapping("api/parking_spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotRepository _parkingSpotRepository;

    @GetMapping(produces = "application/json")
    public @ResponseBody Iterable<ParkingSpot> findAll() {
        Iterable<ParkingSpot> spots = _parkingSpotRepository.findAll();
        return spots;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpot> findById(@PathVariable long id) {
        Optional<ParkingSpot> oSpot = _parkingSpotRepository.findById(id);
        if (!oSpot.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(oSpot.get());
    }

    
    @GetMapping("/parking/{idParking}")
    public ResponseEntity<List<ParkingSpot>> findByIdParking(@PathVariable Long idParking) {
        return ResponseEntity.status(200).body(_parkingSpotRepository.findByIdParking(idParking));
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<ParkingSpot>> register(@RequestBody ObjectNode spots){ /* Recebe um json com os campos {ammount:, idParking:"", idVehicleType:"", idStatus:""} */
        //TODO: Discutir sobre quem deve chamar este medoto, front ou register de Parking
        
        int idParking = Integer.parseInt(spots.get("idParking").asText());
        ParkingSpot spot;

        try{
            for(int i=1; i<=Integer.parseInt(spots.get("ammount").asText()); i++){
                spot = new ParkingSpot();
                spot.setIdParking(idParking);
                spot.setIdVehicleType(Integer.parseInt(spots.get("idVehicleType").asText()));
                spot.setIdStatus(Integer.parseInt(spots.get("idStatus").asText()/* Status Available*/));
                spot.setName(String.valueOf(i));
                _parkingSpotRepository.save(spot);
            }
        }catch(NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ammount has an invalid value");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(_parkingSpotRepository.findByIdParking(idParking));
    }

    @PutMapping()
    public ResponseEntity<ParkingSpot> update(@RequestBody ParkingSpot newParkingSpot){
        Optional<ParkingSpot> oSpot = _parkingSpotRepository.findById(newParkingSpot.getId());
        if(oSpot.isPresent()) {
            _parkingSpotRepository.save(newParkingSpot);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ParkingSpot> delete(long id){
        Optional<ParkingSpot> oSpot = _parkingSpotRepository.findById(id);
        if(oSpot.isPresent()){
            _parkingSpotRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}