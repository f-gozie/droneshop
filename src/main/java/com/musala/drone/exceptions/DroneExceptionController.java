package com.musala.drone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DroneExceptionController {
    @ExceptionHandler(value = InvalidValueException.class)
    public ResponseEntity<Object> exception(InvalidValueException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NonMatchingRegexException.class)
    public ResponseEntity<Object> exception(NonMatchingRegexException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BatteryTooLowException.class)
    public ResponseEntity<Object> exception(BatteryTooLowException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidChoiceException.class)
    public ResponseEntity<Object> exception(InvalidChoiceException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
