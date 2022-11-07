package com.musala.drone.models.converters;

import com.musala.drone.models.dto.DroneDto;
import com.musala.drone.models.entity.DroneEntity;
import com.musala.drone.models.request.DroneRequest;
import com.musala.drone.utils.enums.DroneModel;
import com.musala.drone.utils.enums.DroneState;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DroneConverter {

    public DroneEntity convertRequestToEntity(DroneRequest request) {
        if (request == null) {
            return null;
        }
        return DroneEntity.builder()
                .id(request.getId())
                .serialNumber(request.getSerialNumber())
                .model(DroneModel.valueOf(request.getModel().toString()))
                .weightLimit(request.getWeightLimit())
                .batteryCapacity(request.getBatteryCapacity())
                .state(DroneState.valueOf(request.getState().toString()))
                .medications(request.getMedications())
                .build();
    }

    public DroneDto convertEntityToDto(DroneEntity entity) {
        return DroneDto.builder()
                .id(entity.getId())
                .serialNumber(entity.getSerialNumber())
                .model(entity.getModel().toString())
                .weightLimit(entity.getWeightLimit())
                .batteryCapacity(entity.getBatteryCapacity())
                .state(entity.getState().toString())
                .medications(entity.getMedications())
                .build();
    }

    public List<DroneDto> convertEntityListToDtoList(List<DroneEntity> droneEntities) {
        return droneEntities.stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
