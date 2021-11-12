package com.one234gift.employeeService.employee.query.application;

import com.one234gift.employeeService.employee.domain.read.EmployeeModel;

import java.util.Optional;

public interface QueryEmployeeRepository {
    Optional<EmployeeModel> findByPhone(String phone);
    void save(EmployeeModel employeeModel);
}
