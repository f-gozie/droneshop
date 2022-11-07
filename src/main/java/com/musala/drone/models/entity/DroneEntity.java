package com.musala.drone.models.entity;


import javax.persistence.*;
import javax.validation.constraints.Max;

import com.musala.drone.utils.enums.DroneModel;
import com.musala.drone.utils.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "drone", uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Max(500)
    @Column
    private Integer weightLimit;

    @Column
    private Integer batteryCapacity;

    @Column
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @Column
    @ElementCollection
    private Set<Long> medications;

}
