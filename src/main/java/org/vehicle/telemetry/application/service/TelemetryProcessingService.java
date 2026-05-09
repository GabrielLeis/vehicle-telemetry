package org.vehicle.telemetry.application.service;

import org.vehicle.telemetry.domain.model.VehicleAlert;
import org.vehicle.telemetry.domain.model.VehicleTelemetry;
import org.vehicle.telemetry.domain.port.in.ProcessTelemetryUseCase;
import org.vehicle.telemetry.domain.port.out.AlertPublisherPort;
import org.vehicle.telemetry.domain.port.out.TelemetryRepositoryPort;

public class TelemetryProcessingService implements ProcessTelemetryUseCase {

    private final TelemetryRepositoryPort repositoryPort;
    private final AlertPublisherPort alertPublisherPort;

    private static final double MAX_SAFE_TEMP = 110.0;

    public TelemetryProcessingService(TelemetryRepositoryPort repositoryPort, AlertPublisherPort alertPublisherPort){
        this.repositoryPort = repositoryPort;
        this.alertPublisherPort = alertPublisherPort;
    }

    @Override
    public void process(VehicleTelemetry telemetry){
        // Guardamos el historial de la telemetría
        repositoryPort.save(telemetry);

        // Verificamos temperatura maxima del motor
        if(telemetry.getEngineTemperature() > MAX_SAFE_TEMP) {
            VehicleAlert alert = new VehicleAlert(
                    telemetry.getVehicleId(),
                    "HIGH_TEMPERATURE",
                    "CRITICAL"
            );
            alertPublisherPort.publish(alert);
        }
    }
}
