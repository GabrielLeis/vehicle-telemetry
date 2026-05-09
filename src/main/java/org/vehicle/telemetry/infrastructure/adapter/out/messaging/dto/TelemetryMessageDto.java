package org.vehicle.telemetry.infrastructure.adapter.out.messaging.dto;

public record TelemetryMessageDto(
        String vin, // vehicle identification number
        double latitude,
        double longitude,
        double speed,
        double engineTemperature
) {}
