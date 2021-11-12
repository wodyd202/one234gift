package com.one234gift.employeeService.employee.command.event;

import com.one234gift.employeeService.employee.domain.value.*;
import com.one234gift.employeeService.employee.domain.value.EmployeeName;
import lombok.Getter;

/**
 * 사원 등록 이벤트
 */
@Getter
public class RegisteredEmployeeEvent {
    private String phone, name, password;
    private EmployeePosition role;
    private EmployeeState state;

    public RegisteredEmployeeEvent(Phone phone,
                                   EmployeeName name,
                                   Password password,
                                   EmployeePosition position) {
        this.phone = phone.get();
        this.name = name.get();
        this.role = position;
        this.state = EmployeeState.WORK;
        this.password = password.get();
    }
}
