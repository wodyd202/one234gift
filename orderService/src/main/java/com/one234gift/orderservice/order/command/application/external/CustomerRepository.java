package com.one234gift.orderservice.order.command.application.external;

import com.one234gift.orderservice.config.FeignClientConfig;
import com.one234gift.orderservice.order.domain.value.CustomerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customerService",
             url = "${services.customer.url}",
             fallbackFactory = CustomerRepositoryFallbackFactory.class,
             configuration = FeignClientConfig.class)
public interface CustomerRepository {
    @GetMapping("api/customer/{customerId}")
    Optional<CustomerInfo> findById(@PathVariable long customerId);
}

