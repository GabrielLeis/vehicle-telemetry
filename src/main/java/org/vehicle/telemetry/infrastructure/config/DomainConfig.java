package org.vehicle.telemetry.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vehicle.telemetry.application.service.TelemetryProcessingService;
import org.vehicle.telemetry.domain.port.out.AlertPublisherPort;
import org.vehicle.telemetry.domain.port.out.TelemetryRepositoryPort;

@Configuration
public class DomainConfig {

    @Bean
    public TelemetryProcessingService telemetryProcessingService(
            TelemetryRepositoryPort repositoryPort,
            AlertPublisherPort alertPublisherPort){

        return new TelemetryProcessingService(repositoryPort, alertPublisherPort);

    }
}
