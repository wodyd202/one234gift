package com.one234gift.employeeService.employee.command.event;

import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import com.one234gift.employeeService.employee.domain.value.EmployeeState;
import lombok.Builder;
import lombok.Getter;

/**
 * 사원 등록 이벤트
 */
@Getter
public class JoinedEmployeeEvent {
    private String phone, name, password;
    private EmployeePosition role;
    private EmployeeState state;

    @Builder
    public JoinedEmployeeEvent(String phone,
                               String name,
                               String password,
                               EmployeePosition position) {
        this.phone = phone;
        this.name = name;
        this.role = position;
        this.state = EmployeeState.WORK;
        this.password = password;
    }
}
