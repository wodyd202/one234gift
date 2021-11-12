package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.domain.exception.EmployeeNotFoundException;
import com.one234gift.employeeService.employee.domain.value.Phone;

public class EmployeeServiceHelper {
    public static Employee findByPhone(EmployeeRepository employeeRepository, Phone phone) {
        return employeeRepository.findByPhone(phone).orElseThrow(EmployeeNotFoundException::new);
    }
}
