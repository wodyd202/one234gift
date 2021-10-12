package com.one234gift.orderservice.command.infrastructure;

import com.one234gift.orderservice.command.application.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!test")
public class KafkaProducer implements Producer {
    @Value("${order.topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String payload) {
        log.info("send event {} to topic {}", payload, TOPIC);
        kafkaTemplate.send(TOPIC, payload);
    }
}
