package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.external.Employee;
import com.one234gift.customerservice.customer.command.application.external.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements EmployeeRepository {
    @Override
    public Optional<Employee> getEmployee(String employee) {
        return Optional.of(new Employee(employee,employee));
    }
}
