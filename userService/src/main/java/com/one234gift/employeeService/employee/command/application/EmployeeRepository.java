package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.domain.value.Phone;

import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee employee);
    boolean existsByPhone(Phone phone);
    Optional<Employee> findByPhone(Phone phone);
}
