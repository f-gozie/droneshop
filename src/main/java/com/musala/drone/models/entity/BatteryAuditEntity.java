package com.musala.drone.models.entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "battery_audit")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteryAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "battery_level")
    private Integer batteryLevel;

    @Column(name = "drone_id")
    private Long droneId;

    @Column(name = "timestamp")
    private LocalDate timestamp;

}
