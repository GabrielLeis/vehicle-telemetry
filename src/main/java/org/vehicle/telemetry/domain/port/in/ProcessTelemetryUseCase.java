package org.vehicle.telemetry.domain.port.in;

import org.vehicle.telemetry.domain.model.VehicleTelemetry;

public interface ProcessTelemetryUseCase {

    void process(VehicleTelemetry vehicle);
}
