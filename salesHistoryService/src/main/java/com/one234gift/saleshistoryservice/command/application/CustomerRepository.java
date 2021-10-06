package com.one234gift.saleshistoryservice.command.application;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRepository {
    @GetMapping("api/customer/{customerId}/exist")
    boolean existByCustomer(@PathVariable long customerId);
}
