package com.musala.drone.exceptions;

public class BatteryTooLowException extends RuntimeException {
    public BatteryTooLowException(String message) {
        super(message);
    }
}