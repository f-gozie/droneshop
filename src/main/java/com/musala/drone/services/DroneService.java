package com.musala.drone.services;

import com.musala.drone.models.dto.DroneDto;
import com.musala.drone.models.dto.MedicationDto;
import com.musala.drone.models.request.DroneRequest;
import com.musala.drone.models.request.LoadRequest;

import java.util.List;

public interface DroneService {

    DroneDto createDrone(DroneRequest request) throws Exception;

    List<DroneDto> getAllDrones() throws Exception;

    DroneDto loadMedication(LoadRequest request) throws Exception;

    List<MedicationDto> getMedicationsByDroneId(Long id) throws Exception;

    List<DroneDto> getAvailableDronesForLoading() throws Exception;

    Integer getBatteryByDroneId(Long id) throws Exception;
}
