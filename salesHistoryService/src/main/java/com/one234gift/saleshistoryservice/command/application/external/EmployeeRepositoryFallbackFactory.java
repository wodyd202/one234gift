package com.one234gift.saleshistoryservice.command.application.external;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class EmployeeRepositoryFallbackFactory implements FallbackFactory<EmployeeRepository> {
    @Override
    public EmployeeRepository create(Throwable throwable) {
        return new EmployeeRepository() {
            @Override
            public Optional<Employee> getEmployee(String userId) {
                log.info("employee-service request error : {}", throwable.getMessage());
                return Optional.empty();
            }
        };
    }
}
