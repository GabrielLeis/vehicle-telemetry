package org.vehicle.telemetry.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.vehicle.telemetry.domain.model.VehicleTelemetry;
import org.vehicle.telemetry.domain.port.out.TelemetryRepositoryPort;

import java.util.UUID;

@Repository
public class TelemetryPersistenceAdapter implements TelemetryRepositoryPort {

    private final TelemetryRepository telemetryRepository;

    public TelemetryPersistenceAdapter(TelemetryRepository telemetryRepository){
        this.telemetryRepository = telemetryRepository;
    }

    @Override
    public void save(VehicleTelemetry telemetry){
        TelemetryJpaEntity entity = new TelemetryJpaEntity(
                UUID.randomUUID().toString(),
                telemetry.getVehicleId(),
                telemetry.getLatitude(),
                telemetry.getLongitude(),
                telemetry.getSpeed(),
                telemetry.getEngineTemperature(),
                telemetry.getTimestamp()
        );

        telemetryRepository.save(entity);
    }
}
