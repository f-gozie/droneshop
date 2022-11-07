package com.musala.drone.services.impl;

import com.musala.drone.models.converters.BatteryAuditConverter;
import com.musala.drone.models.dto.BatteryAuditDto;
import com.musala.drone.models.entity.BatteryAuditEntity;
import com.musala.drone.models.entity.DroneEntity;
import com.musala.drone.repository.BatteryAuditRepository;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.services.BatteryAuditService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class BatteryAuditServiceImpl implements BatteryAuditService {

    private final BatteryAuditRepository batteryAuditRepository;
    private final DroneRepository droneRepository;
    private final BatteryAuditConverter batteryAuditConverter;
    private final BatteryAuditDto batteryAuditDto;

    public BatteryAuditServiceImpl(BatteryAuditRepository batteryAuditRepository, DroneRepository droneRepository, BatteryAuditConverter batteryAuditConverter) {
        this.batteryAuditRepository = batteryAuditRepository;
        this.droneRepository = droneRepository;
        this.batteryAuditConverter = batteryAuditConverter;
        this.batteryAuditDto = BatteryAuditDto.builder().build();
    }

    @Override
    public void createBatteryAudit() {
        List<DroneEntity> drones = droneRepository.findAll();
        for (DroneEntity drone : drones) {
            batteryAuditDto.setBatteryLevel(drone.getBatteryCapacity());
            batteryAuditDto.setDroneId(drone.getId());
            batteryAuditDto.setTimestamp(LocalDate.now());
            BatteryAuditEntity batteryEntity = batteryAuditConverter.convertDtoToEntity(batteryAuditDto);
            batteryAuditRepository.save(batteryEntity);
        }
    }
}
