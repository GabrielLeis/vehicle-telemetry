package org.vehicle.telemetry.domain.port.out;

import org.vehicle.telemetry.domain.model.VehicleAlert;

public interface AlertPublisherPort {

    void publish(VehicleAlert alert);
}
