package com.musala.drone.controllers;

import com.musala.drone.models.dto.MedicationDto;
import com.musala.drone.models.request.MedicationRequest;
import com.musala.drone.services.MedicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping("/medications")
    public ResponseEntity<MedicationDto> createMedication(@RequestBody MedicationRequest request) throws Exception {
        MedicationDto medicationDto = medicationService.createMedication(request);
        return new ResponseEntity<>(medicationDto, HttpStatus.CREATED);
    }

    @GetMapping("/medications/{id}")
    public ResponseEntity<MedicationDto> getMedicationById(@PathVariable Long id) throws Exception {
        MedicationDto medicationDto = medicationService.getMedicationById(id);
        return new ResponseEntity<>(medicationDto, HttpStatus.OK);
    }

    @GetMapping("/medications")
    public ResponseEntity<List<MedicationDto>> getAllMedications() throws Exception {
        List<MedicationDto> medicationDtos = medicationService.getAllMedications();
        return new ResponseEntity<>(medicationDtos, HttpStatus.OK);
    }
}
