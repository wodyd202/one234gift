package com.one234gift.orderservice.order.command.application.external;

import com.one234gift.orderservice.config.FeignClientConfig;
import com.one234gift.orderservice.order.domain.value.SalesUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "EMPLOYEE-SERVICE",
             fallbackFactory = EmployeeRepositoryFallbackFactory.class,
             configuration = FeignClientConfig.class)
public interface UserRepository {
    @GetMapping("api/user/{employeeId}")
    Optional<SalesUser> getEmployee(@PathVariable String employeeId);
}
