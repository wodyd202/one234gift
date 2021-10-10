package com.one234gift.customerhistoryservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerHistoryServiceApplicationTests {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Value("${customer-history.topic}")
    private String TOPIC;

    @Test
    void contextLoads() {
        kafkaProducer.send(TOPIC, "dlwodyd");
    }

}
