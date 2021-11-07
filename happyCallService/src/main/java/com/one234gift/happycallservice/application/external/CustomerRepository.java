package com.one234gift.happycallservice.application.external;

import com.one234gift.happycallservice.config.FeignClientConfig;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "CUSTOMER-SERVICE",
        fallbackFactory = CustomerRepositoryFallbackFactory.class,
        configuration = FeignClientConfig.class)
public interface CustomerRepository {

    @GetMapping("api/customer/{customerId}")
    Optional<CustomerInfo> findById(@PathVariable long customerId);
}
