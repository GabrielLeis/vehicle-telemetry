package org.vehicle.telemetry.domain.exception;

public class InvalidTelemetryException extends RuntimeException{

    public InvalidTelemetryException(String message){
        super(message);
    }
}
