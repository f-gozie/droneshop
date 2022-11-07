package com.musala.drone.controllers;

import com.musala.drone.models.dto.DroneDto;
import com.musala.drone.models.dto.MedicationDto;
import com.musala.drone.models.request.DroneRequest;
import com.musala.drone.models.request.LoadRequest;
import com.musala.drone.services.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/drones")
    public ResponseEntity<DroneDto> createDrone(@RequestBody DroneRequest request) throws Exception {
        DroneDto droneDto = droneService.createDrone(request);
        return new ResponseEntity<>(droneDto, HttpStatus.CREATED);
    }

    @GetMapping("/drones")
    public ResponseEntity<List<DroneDto>> getAllMedications() throws Exception {
        List<DroneDto> droneDtos = droneService.getAllDrones();
        return new ResponseEntity<>(droneDtos, HttpStatus.OK);
    }

    @PostMapping("/drones/load")
    public ResponseEntity<DroneDto> loadMedication(@RequestBody LoadRequest request) throws Exception {
        DroneDto droneDto = droneService.loadMedication(request);
        return new ResponseEntity<>(droneDto, HttpStatus.OK);
    }

    @GetMapping("/drones/{id}/medications")
    public ResponseEntity<List<MedicationDto>> getMedicationsByDroneId(@PathVariable Long id) throws Exception {
        List<MedicationDto> medicationDtos = droneService.getMedicationsByDroneId(id);
        return new ResponseEntity<>(medicationDtos, HttpStatus.OK);
    }

    @GetMapping("/drones/available-for-loading")
    public ResponseEntity<List<DroneDto>> getAvailableMedicationsForLoading() throws Exception {
        List<DroneDto> droneDtos = droneService.getAvailableDronesForLoading();
        return new ResponseEntity<>(droneDtos, HttpStatus.OK);
    }

    @GetMapping("/drones/{id}/battery")
    public ResponseEntity<Integer> getBatteryByDroneId(@PathVariable Long id) throws Exception {
        Integer battery = droneService.getBatteryByDroneId(id);
        return new ResponseEntity<>(battery, HttpStatus.OK);
    }
}
