package org.vehicle.telemetry.domain.model;

import org.vehicle.telemetry.domain.exception.InvalidTelemetryException;

import java.time.Instant;

public class VehicleTelemetry {

    private final String vehicleId;
    private final double latitude;
    private final double longitude;
    private final double speed;
    private final double engineTemperature;
    private final Instant timestamp;

    public VehicleTelemetry(String vehicleId, double latitude, double longitude, double speed, double engineTemperature){
        validateVehicleId(vehicleId);
        validateSpeed(speed);
        validateCoordinates(latitude, longitude);

        this.vehicleId = vehicleId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.engineTemperature = engineTemperature;
        this.timestamp = Instant.now();
    }

    //Método de validación interna
    private void validateVehicleId(String vehicleId){
        if(vehicleId == null || vehicleId.trim().isEmpty()){
            throw new InvalidTelemetryException("El ID del vehículo no puede ser nulo o vacío");
        }
    }

    private void validateSpeed(double speed){
        if(speed < 0) throw new InvalidTelemetryException("La velocidad no puede ser negativa " + speed);
        if(speed > 400) throw new InvalidTelemetryException("Lectura de velocidad irreal (nuestros coches son son tan rápidos): " + speed);

    }

    private void validateCoordinates(double latitude, double longitude){
        if(latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180){
            throw new InvalidTelemetryException("Coordenadas del GPS inválidas");
        }
    }

    //Getters
    public String getVehicleId(){return this.vehicleId;}
    public double getLatitude(){return this.latitude;}
    public double getLongitude(){return this.longitude;}
    public double getSpeed(){return this.speed;}
    public double getEngineTemperature(){return this.engineTemperature;}
    public Instant getTimestamp(){return this.timestamp;}
}
