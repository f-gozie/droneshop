package com.musala.drone.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoadRequest implements Serializable {
    private Long droneId;
    private Set<Long> medications;
}

