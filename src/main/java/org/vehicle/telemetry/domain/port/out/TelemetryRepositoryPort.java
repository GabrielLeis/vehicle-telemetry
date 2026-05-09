package org.vehicle.telemetry.domain.port.out;

import org.vehicle.telemetry.domain.model.VehicleTelemetry;

public interface TelemetryRepositoryPort {

    void save(VehicleTelemetry vehicle);
}
