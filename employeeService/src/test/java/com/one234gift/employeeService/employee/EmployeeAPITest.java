package com.one234gift.employeeService.employee;

import com.one234gift.employeeService.APITest;
import com.one234gift.employeeService.employee.command.application.EmployeeRepository;
import com.one234gift.employeeService.employee.command.event.JoinedEmployeeEvent;
import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class EmployeeAPITest extends APITest {
    @Autowired protected EmployeeRepository employeeRepository;
    @Autowired protected ApplicationEventPublisher applicationEventPublisher;

    public void persistEmployee(Employee employee) {
        EmployeeModel employeeModel = employee.toModel();
        if(!employeeRepository.existsByPhone(employee.getPhone())){
            employeeRepository.save(employee);
            applicationEventPublisher.publishEvent(JoinedEmployeeEvent.builder()
                    .name(employeeModel.getName())
                    .phone(employeeModel.getPhone())
                    .password(employeeModel.getPassword())
                    .position(employeeModel.getPosition())
                    .build());
        }
    }
}
