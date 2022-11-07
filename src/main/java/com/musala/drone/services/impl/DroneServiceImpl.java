package com.musala.drone.services.impl;

import com.musala.drone.exceptions.BatteryTooLowException;
import com.musala.drone.exceptions.InvalidValueException;
import com.musala.drone.models.converters.DroneConverter;
import com.musala.drone.models.converters.MedicationConverter;
import com.musala.drone.models.dto.DroneDto;
import com.musala.drone.models.dto.MedicationDto;
import com.musala.drone.models.entity.DroneEntity;
import com.musala.drone.models.entity.MedicationEntity;
import com.musala.drone.models.request.DroneRequest;
import com.musala.drone.models.request.LoadRequest;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.services.DroneService;
import com.musala.drone.utils.enums.DroneState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneConverter droneConverter;

    private final MedicationConverter medicationConverter;

    public DroneServiceImpl(DroneRepository droneRepository, DroneConverter droneConverter, MedicationRepository medicationRepository, MedicationConverter medicationConverter) {
        this.droneRepository = droneRepository;
        this.droneConverter = droneConverter;
        this.medicationRepository = medicationRepository;
        this.medicationConverter = medicationConverter;
    }

    @Override
    public DroneDto createDrone(DroneRequest request) throws Exception {
        DroneEntity droneEntity = droneConverter.convertRequestToEntity(request);
        String serialNumber = droneEntity.getSerialNumber();
        if (droneRepository.findById(droneEntity.getId()).isPresent()) {
            throw new InvalidValueException("Drone with this id already exists");
        }
//        if (droneRepository.findBySerialNumber(serialNumber).isPresent()) {
//            throw new InvalidValueException("Drone with this serial number already exists");
//        }
        if (droneEntity.getWeightLimit() < 0) {
            throw new InvalidValueException("Drone weight limit cannot be negative");
        }
        if (droneEntity.getWeightLimit() > 500) {
            throw new InvalidValueException("Drone weight limit cannot be more than 500");
        }
        verifyBatteryAndState(droneEntity.getBatteryCapacity(), droneEntity.getState());
        Set<Long> medications = droneEntity.getMedications();
        validateMedications(medications, droneEntity.getWeightLimit());

        DroneEntity savedDroneEntity = droneRepository.save(droneEntity);
        return droneConverter.convertEntityToDto(savedDroneEntity);
    }

    @Override
    public List<DroneDto> getAllDrones() throws Exception {
        List<DroneEntity> droneEntities = droneRepository.findAll();
        return droneConverter.convertEntityListToDtoList(droneEntities);
    }

    @Override
    public DroneDto loadMedication(LoadRequest request) throws Exception {
        Long droneId = request.getDroneId();
        Optional<DroneEntity> droneEntityOptional = droneRepository.findById(droneId);

        if (droneEntityOptional.isEmpty()) {
            throw new InvalidValueException("Drone with id " + droneId + " does not exist");
        }

        DroneEntity droneEntity = droneEntityOptional.get();

        Set<Long> medications = request.getMedications();
        validateMedications(medications, droneEntity.getWeightLimit(), droneEntity.getMedications());
        droneEntity.getMedications().addAll(medications);
        DroneEntity savedDroneEntity = droneRepository.save(droneEntity);
        return droneConverter.convertEntityToDto(savedDroneEntity);
    }

    @Override
    public List<MedicationDto> getMedicationsByDroneId(Long id) throws Exception {
        Optional<DroneEntity> droneEntityOptional = droneRepository.findById(id);
        if (droneEntityOptional.isEmpty()) {
            throw new InvalidValueException("Drone with id " + id + " does not exist");
        }
        DroneEntity droneEntity = droneEntityOptional.get();
        Set<Long> medicationIds = droneEntity.getMedications();
        List<MedicationEntity> medicationEntities = medicationRepository.findAllById(medicationIds);
        return medicationConverter.convertEntityListToDtoList(medicationEntities);
    }

    @Override
    public List<DroneDto> getAvailableDronesForLoading() throws Exception {
        List<DroneEntity> droneEntities = droneRepository.findAllByState(DroneState.valueOf("LOADING"));
        List<DroneEntity> entities = droneEntities.stream().toList();
        return droneConverter.convertEntityListToDtoList(entities);
    }

    @Override
    public Integer getBatteryByDroneId(Long id) throws Exception {
        Optional<DroneEntity> droneEntityOptional = droneRepository.findById(id);
        if (droneEntityOptional.isEmpty()) {
            throw new InvalidValueException("Drone with id " + id + " does not exist");
        }
        DroneEntity droneEntity = droneEntityOptional.get();
        return droneEntity.getBatteryCapacity();
    }

    public Boolean doesDroneExist(String serialNumber) {
        Optional<DroneEntity> droneEntity = droneRepository.findBySerialNumber(serialNumber);
        return droneEntity.isPresent();
    }

    public void validateMedications(Set<Long> medications, Integer weightLimit) throws Exception {
        Integer totalLimit = 0;
        for (Long medicationId : medications) {
            Optional<MedicationEntity> medicationEntity = medicationRepository.findById(medicationId);
            if (medicationEntity.isEmpty()) {
                throw new InvalidValueException("Medication with id " + medicationId + " does not exist");
            }
            totalLimit += medicationEntity.get().getWeight();
        }
        if (totalLimit > weightLimit) {
            throw new InvalidValueException("Total weight of medications cannot be more than drone weight limit");
        }
    }

    public void validateMedications(Set<Long> medications, Integer weightLimit, Set<Long> existingMedications) {
        Integer totalLimit = 0;
        for (Long medicationId : medications) {
            Optional<MedicationEntity> medicationEntity = medicationRepository.findById(medicationId);
            if (medicationEntity.isEmpty()) {
                throw new InvalidValueException("Medication with id " + medicationId + " does not exist");
            }
            if (existingMedications.contains(medicationId)) {
                throw new InvalidValueException("Medication with id " + medicationId + " is already loaded on the drone");
            }
            totalLimit += medicationEntity.get().getWeight();
        }
        if (!existingMedications.isEmpty()) {
            totalLimit += existingMedications.stream().map(medicationRepository::findById).map(Optional::get).map(MedicationEntity::getWeight).reduce(0, Integer::sum);
        }
        if (totalLimit > weightLimit) {
            throw new InvalidValueException("Total weight of medications cannot be more than drone weight limit");
        }
    }

    public void verifyBatteryAndState(Integer battery, DroneState state) {
        if ((battery < 25) && (state == DroneState.valueOf("LOADING"))) {
            throw new BatteryTooLowException("Battery capacity cannot be less than 25% if drone is in loading state");
        }
    }
}
