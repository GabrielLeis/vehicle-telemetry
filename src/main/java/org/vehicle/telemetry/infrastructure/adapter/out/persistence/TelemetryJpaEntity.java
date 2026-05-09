package org.vehicle.telemetry.infrastructure.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_telemetry")
public class TelemetryJpaEntity {
    // Lombok ya crea los constructores y los Getters y Setters.
    @Id
    private String id;
    private String vehicleId;
    private double latitude;
    private double longitude;
    private double speed;
    private double engineTemperature;
    private Instant timestamp;
}
