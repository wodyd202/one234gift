package com.one234gift.employeeService;

import com.one234gift.employeeService.employee.command.application.JoinEmployeeService;
import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired private JoinEmployeeService joinEmployeeService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        JoinEmployee joinEmployee = JoinEmployee.builder()
                .phone("123-0000-0000")
                .name("경리담당자")
                .position(EmployeePosition.ACCOUNTING_EMPLOYEE)
                .build();
        joinEmployeeService.join(joinEmployee);
    }
}
