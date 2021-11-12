package com.one234gift.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("!test")
@EnableDiscoveryClient
@Configuration
public class EurekaClientConfig {

}
