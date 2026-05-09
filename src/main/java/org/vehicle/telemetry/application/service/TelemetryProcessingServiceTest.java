package org.vehicle.telemetry.application.service;

import org.vehicle.telemetry.domain.model.VehicleAlert;
import org.vehicle.telemetry.domain.model.VehicleTelemetry;
import org.vehicle.telemetry.domain.port.out.AlertPublisherPort;
import org.vehicle.telemetry.domain.port.out.TelemetryRepositoryPort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TelemetryProcessingServiceTest {

    private TelemetryRepositoryPort repositoryPortMock;
    private AlertPublisherPort alertPublisherPortMock;
    private TelemetryProcessingService service;

    @BeforeEach
    void setUp(){
        repositoryPortMock = mock(TelemetryRepositoryPort.class);
        alertPublisherPortMock = mock(AlertPublisherPort.class);

        service = new TelemetryProcessingService(repositoryPortMock, alertPublisherPortMock);
    }

    @Test
    void whenTemperatureIsNormal_thenOnlySaveTelemetry(){
        // Preparamos el objeto
        VehicleTelemetry normalTelemetry = new VehicleTelemetry("VIN123", 42.0, -8.0, 100.0, 90.0);
        // Procesamos la acción
        service.process(normalTelemetry);
        // Verificamos
        verify(repositoryPortMock, times(1)).save(normalTelemetry);
        verify(alertPublisherPortMock, never()).publish(any());
    }

    @Test
    void whenTemperatureHigh_thenSaveTelemetryAndPublishAlert(){
        // Preparamos el objeto (MAX TEMP 110)
        VehicleTelemetry highTempTelemetry = new VehicleTelemetry("VIN123", 42.0, -8.0, 120, 115.0);
        // Procesamos la acción
        service.process(highTempTelemetry);
        // Verificamos
        verify(repositoryPortMock, times(1)).save(highTempTelemetry);
        // Capturamos el argumento para verificar sus valores internos
        ArgumentCaptor<VehicleAlert> alertCaptor = ArgumentCaptor.forClass(VehicleAlert.class);
        verify(alertPublisherPortMock, times(1)).publish(alertCaptor.capture());

        VehicleAlert generatedAlert = alertCaptor.getValue();
        assertEquals("VIN123", generatedAlert.getVehicleId());
        assertEquals("HIGH_TEMPERATURE", generatedAlert.getType());
        assertEquals("CRITICAL", generatedAlert.getSeverity());
    }
}
