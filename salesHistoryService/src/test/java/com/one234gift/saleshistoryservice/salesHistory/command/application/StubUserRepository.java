package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Employee;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements EmployeeRepository {
    @Override
    public Optional<Employee> getEmployee(String userId) {
        return Optional.of(Employee.builder().phone(userId).build());
    }
}
