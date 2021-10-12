package com.one234gift.customerservice.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    Retryer retryer(){
        return Retryer.NEVER_RETRY;
    }
}
