package com.one234gift.customerhistoryservice.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class CustomerHistoryEventListener {
    @Autowired
    private CustomerHistoryRepository customerHistoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "${customer-history.topic}", groupId = "${customer-history.topic}")
    void handle(String event) {
        try{
            CustomerHistoryEvent customerHistoryEvent = objectMapper.readValue(event, CustomerHistoryEvent.class);
            CustomerHistory customerHistory = CustomerHistory.register(customerHistoryEvent);
            customerHistoryRepository.save(customerHistory);
            log.info("save customer history : {}", customerHistoryEvent);
        }catch (Exception e){
        }
    }
}
