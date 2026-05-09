package org.vehicle.telemetry.infrastructure.adapter.out.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.vehicle.telemetry.domain.model.VehicleAlert;
import org.vehicle.telemetry.domain.port.out.AlertPublisherPort;
import org.vehicle.telemetry.infrastructure.adapter.out.messaging.dto.AlertMessageDto;
import tools.jackson.databind.ObjectMapper;

@Component
public class AlertKafkaProducerAdapter implements AlertPublisherPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC_ALERTS = "vehicle-alerts";

    public AlertKafkaProducerAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(VehicleAlert alert){
        AlertMessageDto dto = new AlertMessageDto(
                alert.getVehicleId(),
                alert.getType(),
                alert.getSeverity(),
                alert.getTimestamp().toString()
        );

        try{
            String payload = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send(TOPIC_ALERTS, alert.getVehicleId(), payload);
        } catch (RuntimeException e){
            throw new RuntimeException("Error fatal serializando la alerta para kafka", e);
        }
    }
}
