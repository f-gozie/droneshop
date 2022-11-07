package com.musala.drone.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDto implements Serializable {
    private Long id;
    private String name;
    private Integer weight;
    private String code;
//    private byte[] image;
}
