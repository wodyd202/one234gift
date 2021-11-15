package com.one234gift.employeeService.employee.command.event;

import com.one234gift.employeeService.employee.domain.value.Phone;
import lombok.Getter;

/**
 * 사원 재입사 이벤트
 */
@Getter
public class ComebackedEmployeeEvent extends AbstractEmployeeEvent {
    public ComebackedEmployeeEvent(String phone) {
        super(phone);
    }
}
