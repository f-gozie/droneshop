package com.musala.drone.models.request;

import lombok.Data;
import lombok.Builder;

import java.io.Serializable;
import java.util.Set;


@Data
@Builder
public class LoadRequest implements Serializable {
    private Long droneId;
    private Set<Long> medications;
}

