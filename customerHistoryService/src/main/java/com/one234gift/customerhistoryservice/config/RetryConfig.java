package com.one234gift.customerhistoryservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@Configuration
@Profile("!test")
public class RetryConfig {
}
