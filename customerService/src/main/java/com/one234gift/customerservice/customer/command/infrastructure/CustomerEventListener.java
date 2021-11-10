package com.one234gift.customerservice.customer.command.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.customerservice.customer.command.application.event.ChangedCustomerEvent;
import com.one234gift.customerservice.customer.command.infrastructure.model.CustomerHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Async("kafkaExecutor")
@Slf4j
@Profile("!test")
@Component
public class CustomerEventListener {
    @Value("${customer-history.topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @EventListener
    protected void handle(ChangedCustomerEvent event) throws Exception {
        CustomerHistory customerHistory = new CustomerHistory(event.getId());
        invoke(event, customerHistory);
        String eventInfo = objectMapper.writeValueAsString(customerHistory);

        log.info("send event {} to topic {}", eventInfo, TOPIC);
        kafkaTemplate.send(TOPIC, eventInfo);
    }

    private final String ON = "on";
    private void invoke(ChangedCustomerEvent event, CustomerHistory customerHistory) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = CustomerHistory.class.getDeclaredMethod(ON, event.getClass());
        method.setAccessible(true);
        method.invoke(customerHistory, event);
    }
}
