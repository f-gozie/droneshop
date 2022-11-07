package com.musala.drone.models.converters;

import com.musala.drone.models.dto.MedicationDto;
import com.musala.drone.models.entity.MedicationEntity;
import com.musala.drone.models.request.MedicationRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicationConverter {

    public MedicationEntity convertRequestToEntity(MedicationRequest request) {
        if (request == null) {
            return null;
        }
        return MedicationEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .weight(request.getWeight())
                .code(request.getCode())
//                .image(request.getImage())
                .build();
    }

    public MedicationDto convertEntityToDto(MedicationEntity entity) {
        if (entity == null) {
            return null;
        }
        return MedicationDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .weight(entity.getWeight())
                .code(entity.getCode())
//                .image(entity.getImage())
                .build();
    }

    public List<MedicationDto> convertEntityListToDtoList(List<MedicationEntity> medicationEntities) {
        return medicationEntities.stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
