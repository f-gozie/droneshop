package com.musala.drone.utils.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum DroneState {
    IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;
}