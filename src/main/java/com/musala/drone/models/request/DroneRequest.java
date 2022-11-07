package com.musala.drone.models.request;

import com.musala.drone.models.entity.MedicationEntity;
import lombok.Data;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Data
@Builder
public class DroneRequest implements Serializable {
    private Long id;
    private String serialNumber;
    private String model;
    private Integer weightLimit;
    private Integer batteryCapacity;
    private String state;
    private Set<Long> medications;
}
