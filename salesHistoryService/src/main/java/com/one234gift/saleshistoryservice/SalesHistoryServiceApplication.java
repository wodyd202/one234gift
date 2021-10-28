package com.one234gift.saleshistoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SalesHistoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesHistoryServiceApplication.class, args);
    }

}
