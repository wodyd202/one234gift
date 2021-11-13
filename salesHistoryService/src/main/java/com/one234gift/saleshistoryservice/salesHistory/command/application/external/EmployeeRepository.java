package com.one234gift.saleshistoryservice.salesHistory.command.application.external;

import com.one234gift.saleshistoryservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "EMPLOYEE-SERVICE",
             fallbackFactory = EmployeeRepositoryFallbackFactory.class,
             configuration = FeignClientConfig.class)
public interface EmployeeRepository {
    @GetMapping("api/employee/{employeeId}")
    Optional<Employee> getEmployee(@PathVariable String employeeId);
}
