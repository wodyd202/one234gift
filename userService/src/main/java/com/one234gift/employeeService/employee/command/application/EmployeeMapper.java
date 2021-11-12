package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import com.one234gift.employeeService.employee.domain.value.Phone;
import com.one234gift.employeeService.employee.domain.value.EmployeeName;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    /**
     * @param joinEmployee
     * @param passwordEncoder
     */
    public Employee mapFrom(JoinEmployee joinEmployee, PasswordEncoder passwordEncoder) {
        return Employee.builder()
                .phone(new Phone(joinEmployee.getPhone()))
                .name(new EmployeeName(joinEmployee.getName()))
                .passwordEncoder(passwordEncoder)
                .role(joinEmployee.getPosition())
                .build();
    }
}
