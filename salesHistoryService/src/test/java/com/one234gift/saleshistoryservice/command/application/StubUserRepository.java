package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.external.Employee;
import com.one234gift.saleshistoryservice.command.application.external.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements EmployeeRepository {
    private Employee writer;

    @Override
    public Optional<Employee> getEmployee(String userId) {
        return Optional.of(writer);
    }

    public void save(String userId){
        writer = new Employee("작성자", userId);
    }
}
