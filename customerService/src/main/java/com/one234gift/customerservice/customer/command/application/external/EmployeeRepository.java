package com.one234gift.customerservice.customer.command.application.external;

import com.one234gift.customerservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "EMPLOYEE-SERVICE", configuration = FeignClientConfig.class, fallbackFactory = EmployeeRepositoryFallbackFactory.class)
public interface EmployeeRepository {
    @GetMapping("api/employee/{employeeId}")
    Optional<Employee> getEmployee(@PathVariable String employeeId);
}
