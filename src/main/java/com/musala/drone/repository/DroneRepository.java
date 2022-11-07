package com.musala.drone.repository;

import com.musala.drone.models.entity.DroneEntity;
import com.musala.drone.utils.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<DroneEntity, Long> {
        Optional<DroneEntity> findBySerialNumber(String serialNumber);
        List<DroneEntity> findAllByState(DroneState state);
}