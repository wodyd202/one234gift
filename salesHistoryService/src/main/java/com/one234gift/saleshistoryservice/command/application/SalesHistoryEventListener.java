package com.one234gift.saleshistoryservice.command.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.saleshistoryservice.command.application.event.CallReserved;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("!test")
public class SalesHistoryEventListener {

    @Autowired
    private Producer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @EventListener
    void handle(CallReserved event) throws JsonProcessingException {
        producer.publish(objectMapper.writeValueAsString(event));
    }
}
