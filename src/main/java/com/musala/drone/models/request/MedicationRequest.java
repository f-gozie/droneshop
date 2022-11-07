package com.musala.drone.models.request;

import lombok.Data;
import lombok.Builder;

import java.io.Serializable;


@Data
@Builder
public class MedicationRequest implements Serializable {
    private Long id;
    private String name;
    private Integer weight;
    private String code;
//    private byte[] image;
}
