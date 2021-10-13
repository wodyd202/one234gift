package com.one234gift.happycallservice.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.model.OrderEvent;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
public class OrderEventListener {
    @Autowired
    private HappyCallRepository happyCallRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${happy-call.topic}")
    public void handle(String message) throws Exception {
        System.out.println(message);
        OrderEvent orderEvent = objectMapper.readValue(message, OrderEvent.class);
        HappyCall happyCall = HappyCall.register(orderEvent);
        happyCallRepository.save(happyCall);
        HappyCallModel happyCallModel = happyCall.toModel();
        log.info("save happy call : {}" , happyCallModel);
    }
}
