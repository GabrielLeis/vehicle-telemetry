package org.vehicle.telemetry.infrastructure.adapter.in.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.vehicle.telemetry.domain.model.VehicleTelemetry;
import org.vehicle.telemetry.domain.port.in.ProcessTelemetryUseCase;
import org.vehicle.telemetry.infrastructure.adapter.out.messaging.dto.TelemetryMessageDto;
import tools.jackson.databind.ObjectMapper;

@Component
public class TelemetryKafkaConsumerAdapter {

    private final ProcessTelemetryUseCase processTelemetryUseCase;
    private final ObjectMapper objectMapper;

    public TelemetryKafkaConsumerAdapter(ProcessTelemetryUseCase processTelemetryUseCase, ObjectMapper objectMapper){
        this.processTelemetryUseCase = processTelemetryUseCase;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "vehicle-telemetry", groupId = "telemetry-processor-group")
    public void consume(String payload){
        try{
            TelemetryMessageDto dto = objectMapper.readValue(payload, TelemetryMessageDto.class);

            VehicleTelemetry telemetry = new VehicleTelemetry(
                    dto.vin(),
                    dto.latitude(),
                    dto.longitude(),
                    dto.speed(),
                    dto.engineTemperature()
            );

            processTelemetryUseCase.process(telemetry);
        } catch (Exception e){
            System.err.println("Fallo al procesar el mensaje de telemetría: " + e.getMessage());
        }
    }
}
