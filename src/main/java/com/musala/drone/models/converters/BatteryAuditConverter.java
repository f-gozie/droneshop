package com.musala.drone.models.converters;

import com.musala.drone.models.dto.BatteryAuditDto;
import com.musala.drone.models.entity.BatteryAuditEntity;
import com.musala.drone.models.entity.DroneEntity;
import com.musala.drone.models.request.DroneRequest;
import com.musala.drone.utils.enums.DroneModel;
import com.musala.drone.utils.enums.DroneState;
import org.springframework.stereotype.Component;

@Component
public class BatteryAuditConverter {

    public BatteryAuditEntity convertDtoToEntity(BatteryAuditDto batteryAuditDto) {
        if (batteryAuditDto == null) {
            return null;
        }
        return BatteryAuditEntity.builder()
                .id(batteryAuditDto.getId())
                .batteryLevel(batteryAuditDto.getBatteryLevel())
                .droneId(batteryAuditDto.getDroneId())
                .timestamp(batteryAuditDto.getTimestamp())
                .build();
    }
}