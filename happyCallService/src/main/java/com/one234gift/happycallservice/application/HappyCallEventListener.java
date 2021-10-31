package com.one234gift.happycallservice.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.happycallservice.domain.model.RegisterHappyCall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("!test")
@Slf4j
@Component
@Transactional
public class HappyCallEventListener {
    @Autowired
    private HappyCallService happyCallService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${sales-history.topic}")
    public void callReserved(String event) throws Exception {
        RegisterHappyCall registerHappyCall = objectMapper.readValue(event, RegisterHappyCall.class);
        happyCallService.registerCallReservation(registerHappyCall);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${happy-call.topic}")
    public void happyCall(String event) throws Exception{
        RegisterHappyCall registerHappyCall = objectMapper.readValue(event, RegisterHappyCall.class);
        happyCallService.registerOrderHappyCall(registerHappyCall);
    }
}
