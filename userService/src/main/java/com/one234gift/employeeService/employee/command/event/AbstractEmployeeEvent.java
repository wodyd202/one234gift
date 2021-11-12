package com.one234gift.employeeService.employee.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract public class AbstractEmployeeEvent {
    private final String phone;
}
