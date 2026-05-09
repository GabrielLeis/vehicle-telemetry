package org.vehicle.telemetry.infrastructure.adapter.out.messaging.dto;


public record AlertMessageDto(
        String vehicleId,
        String alertType,
        String severityLevel,
        String generatedAlert
) {}
