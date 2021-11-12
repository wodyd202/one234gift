package com.one234gift.employeeService.employee;

import com.one234gift.employeeService.APITest;
import com.one234gift.employeeService.employee.command.application.EmployeeRepository;
import com.one234gift.employeeService.employee.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeAPITest extends APITest {
    @Autowired protected EmployeeRepository employeeRepository;

    public void persistEmployee(Employee user) {
        if(!employeeRepository.existsByPhone(user.getPhone())){
            employeeRepository.save(user);
        }
    }
}
