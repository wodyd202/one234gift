package com.one234gift.customerhistoryservice.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("!test")
@Slf4j
public class CustomerHistoryEventListener {
    @Autowired
    private CustomerHistoryService customerHistoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "${customer-history.topic}", groupId = "${customer-history.topic}")
    void handle(String event) throws Exception {
        CustomerHistoryEvent customerHistoryEvent = objectMapper.readValue(event, CustomerHistoryEvent.class);
        customerHistoryService.save(customerHistoryEvent);
    }
}

