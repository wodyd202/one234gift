package com.one234gift.saleshistoryservice.salesHistory.command.application.external;

import com.one234gift.saleshistoryservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE",
             configuration = FeignClientConfig.class,
             fallbackFactory = CustomerRepositoryFallbackFactory.class)
public interface CustomerRepository {
    @GetMapping("api/customer/{customerId}?simple=true")
    Customer getCustomer(@PathVariable long customerId);
}
