package com.one234gift.happycallservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Profile;

@EnableDiscoveryClient
@Profile("!test")
public class EurekaClientConfig {
}
