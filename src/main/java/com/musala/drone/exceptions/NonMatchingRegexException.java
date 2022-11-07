package com.musala.drone.exceptions;

public class NonMatchingRegexException extends RuntimeException {
    public NonMatchingRegexException(String message) {
        super(message);
    }
}