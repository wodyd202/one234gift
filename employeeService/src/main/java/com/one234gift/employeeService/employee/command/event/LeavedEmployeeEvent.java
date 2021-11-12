package com.one234gift.employeeService.employee.command.event;

import com.one234gift.employeeService.employee.domain.value.Phone;
import lombok.Getter;

/**
 * 사원 퇴사 이벤트
 */
@Getter
public class LeavedEmployeeEvent extends AbstractEmployeeEvent {
    public LeavedEmployeeEvent(Phone phone) {
        super(phone.get());
    }
}
