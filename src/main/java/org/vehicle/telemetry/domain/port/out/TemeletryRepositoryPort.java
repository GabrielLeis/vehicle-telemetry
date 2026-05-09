package org.vehicle.telemetry.domain.port.out;

import org.vehicle.telemetry.domain.model.VehicleTelemetry;

public interface TemeletryRepositoryPort {

    public void save(VehicleTelemetry vehicle);
}
