package com.musala.drone.services;

import com.musala.drone.models.dto.MedicationDto;
import com.musala.drone.models.request.MedicationRequest;

import java.util.List;

public interface MedicationService {

    MedicationDto createMedication(MedicationRequest request) throws Exception;

    MedicationDto getMedicationById(Long id) throws Exception;

    List<MedicationDto> getAllMedications() throws Exception;

}
