package com.musala.drone.models.dto;

import com.musala.drone.models.converters.DroneConverter;
import com.musala.drone.models.converters.MedicationConverter;
import com.musala.drone.models.entity.MedicationEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DroneDto implements Serializable {
    private Long id;
    private String serialNumber;
    private String model;
    private Integer weightLimit;
    private Integer batteryCapacity;
    private String state;
    private Set<Long> medications;

//    public List<MedicationDto> getMedications() {
//        // Return list of MedicationDto objects gotten by their IDs
//        return medications.stream()
//                .map(medicationId -> {
//                    SimpleJpaRepository medicationRepository = new SimpleJpaRepository(MedicationEntity.class, null);
//                    MedicationEntity medicationEntity = (MedicationEntity) medicationRepository.findById(medicationId).orElse(null);
//                    MedicationConverter medicationConverter = new MedicationConverter();
//                    return medicationConverter.convertEntityToDto(medicationEntity);
//                })
//                .collect(Collectors.toList());
//    }
}
