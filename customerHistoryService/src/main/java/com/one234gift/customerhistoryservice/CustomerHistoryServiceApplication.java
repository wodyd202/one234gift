package com.one234gift.customerhistoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerHistoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerHistoryServiceApplication.class, args);
    }

}
