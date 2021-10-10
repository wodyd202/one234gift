package com.one234gift.customerservice.command.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.customerservice.command.application.event.ChangedCustomerEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Slf4j
public class CustomerHistoryEventListener {
    @Autowired
    private Producer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @EventListener
    protected void handle(ChangedCustomerEvent event) throws JsonProcessingException {
        producer.publish(objectMapper.writeValueAsString(event));
    }
}
