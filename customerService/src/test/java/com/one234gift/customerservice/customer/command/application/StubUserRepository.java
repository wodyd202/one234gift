package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.external.Employee;
import com.one234gift.customerservice.customer.command.application.external.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements EmployeeRepository {
    private Employee employee;
    @Override
    public Optional<Employee> getEmployee(String userId) {
        return Optional.of(employee);
    }

    public void save(String userId){
        employee = new Employee("담당자명", userId);
    }
}
