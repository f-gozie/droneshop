package com.musala.drone.models.entity;


import javax.persistence.*;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medication")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z\\d-_]+$", message = "Name can only contain letters, numbers and -_")
    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Name can only contain uppercase letters, numbers, and _")
    @Column(name = "code")
    private String code;

//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    @Column(name = "image")
//    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private DroneEntity drone;

}
