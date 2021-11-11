package com.one234gift.saleshistoryservice.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableEurekaClient
@Configuration
@Profile("!test")
public class EurekaClientConfig {
}
