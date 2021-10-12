package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.value.CustomerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRepository {

    @GetMapping("api/customer/{customerId}")
    Optional<CustomerInfo> findById(@PathVariable long customerId);
}

