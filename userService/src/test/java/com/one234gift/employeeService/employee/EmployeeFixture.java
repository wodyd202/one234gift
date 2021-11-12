package com.one234gift.employeeService.employee;

import com.one234gift.employeeService.employee.command.application.EmployeeMapper;
import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EmployeeFixture {
    private static EmployeeMapper employeeMapper = new EmployeeMapper();
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Employee aEmployee(String phone){
        Employee employee = employeeMapper.mapFrom(JoinEmployee.builder()
                .name("영업사원")
                .phone(phone)
                .position(EmployeePosition.SALES_EMPLOYEE)
                .build(), passwordEncoder);
        return employee;
    }

    public static Employee leavedEmployee(String phone){
        Employee employee = aEmployee(phone);
        employee.leave();
        return employee;
    }

    public static JoinEmployee.JoinEmployeeBuilder aRegisterEmployee(){
        return JoinEmployee
                .builder()
                .phone("000-0000-0000")
                .name("사원")
                .position(EmployeePosition.SALES_EMPLOYEE);
    }
}
