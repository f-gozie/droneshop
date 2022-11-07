package com.musala.drone.services.impl;

import com.musala.drone.exceptions.InvalidValueException;
import com.musala.drone.exceptions.NonMatchingRegexException;
import com.musala.drone.models.converters.MedicationConverter;
import com.musala.drone.models.dto.MedicationDto;
import com.musala.drone.models.entity.MedicationEntity;
import com.musala.drone.models.request.MedicationRequest;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.services.MedicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationConverter medicationConverter;

    public MedicationServiceImpl(MedicationRepository medicationRepository, MedicationConverter medicationConverter) {
        this.medicationRepository = medicationRepository;
        this.medicationConverter = medicationConverter;
    }

    @Override
    public MedicationDto createMedication(MedicationRequest request) throws Exception {
        MedicationEntity medicationEntity = medicationConverter.convertRequestToEntity(request);
        if (!medicationEntity.getName().matches("^[a-zA-Z\\d-_]+$")) {
            throw new NonMatchingRegexException("Medication name must contain only letters, numbers, dashes and underscores");
        }
        if (!medicationEntity.getCode().matches("^[A-Z\\d-_]+$")) {
            throw new NonMatchingRegexException("Medication code must contain only uppercase letters, numbers, and underscores");
        }
        Optional<MedicationEntity> existingMed = medicationRepository.findById(medicationEntity.getId());

        if (existingMed.isPresent()) {
            throw new InvalidValueException("Medication with this id already exists");
        }

        MedicationEntity savedMedicationEntity = medicationRepository.save(medicationEntity);
        return medicationConverter.convertEntityToDto(savedMedicationEntity);
    }

    @Override
    public MedicationDto getMedicationById(Long id) throws Exception {
        Optional<MedicationEntity> medicationEntity = medicationRepository.findById(id);
        if (medicationEntity.isEmpty()) {
            throw new InvalidValueException("Medication with this id does not exist");
        }
        return medicationConverter.convertEntityToDto(medicationEntity.get());
    }

    @Override
    public List<MedicationDto> getAllMedications() throws Exception {
        List<MedicationEntity> medicationEntities = medicationRepository.findAll();
        return medicationConverter.convertEntityListToDtoList(medicationEntities);
    }
}
