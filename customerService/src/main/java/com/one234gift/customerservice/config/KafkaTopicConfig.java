package com.one234gift.customerservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class KafkaTopicConfig {
    @Value("${customer-history.topic}")
    private String TOPIC;

    @Bean
    NewTopic newTopic(){
        return new NewTopic(TOPIC, (short) 1, (short) 1);
    }
}
