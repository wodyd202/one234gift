package com.one234gift.orderservice.command.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.orderservice.command.application.event.OrderedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("!test")
public class OrderEventListener {

    @Autowired
    private Producer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @EventListener
    void handle(OrderedEvent event) throws JsonProcessingException {
        producer.publish(objectMapper.writeValueAsString(event));
    }
}
