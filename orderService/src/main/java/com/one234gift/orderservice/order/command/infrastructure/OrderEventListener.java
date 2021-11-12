package com.one234gift.orderservice.order.command.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.orderservice.order.command.application.event.OrderedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("!test")
public class OrderEventListener {
    @Value("${order.topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @EventListener
    void handle(OrderedEvent event) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(event);
        log.info("send event {} to topic {}", payload, TOPIC);
        kafkaTemplate.send(TOPIC, payload);
    }
}
