package com.musala.drone.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryAuditDto implements Serializable {
    private Long id;
    private Integer batteryLevel;
    private Long droneId;
    private LocalDate timestamp;
}
