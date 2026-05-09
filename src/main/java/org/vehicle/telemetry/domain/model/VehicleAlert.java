package org.vehicle.telemetry.domain.model;

import java.time.Instant;
import java.util.UUID;

public class VehicleAlert {

    private final String alertId;
    private final String vehicleId;
    private final String type;
    private final String severity;
    private final Instant timestamp;

    public VehicleAlert(String vehicleId, String type, String severity){
        if(vehicleId == null || type == null || severity == null){
            throw new IllegalArgumentException("Los datos de la alerta no pueden ser nulos.");
        }
        this.alertId = UUID.randomUUID().toString();
        this.vehicleId = vehicleId;
        this.type = type;
        this.severity = severity;
        this.timestamp = Instant.now();
    }

    //Getters
    public String getAlertId(){return alertId;}
    public String getVehicleId(){return vehicleId;}
    public String getType(){return type;}
    public String getSeverity(){return severity;}
    public Instant getTimestamp(){return timestamp;}
}
