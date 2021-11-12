package com.one234gift.employeeService.employee.domain.read;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one234gift.employeeService.employee.command.event.ComebackedEmployeeEvent;
import com.one234gift.employeeService.employee.command.event.LeavedEmployeeEvent;
import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import com.one234gift.employeeService.employee.domain.value.EmployeeState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmployeeModel {
    private String name;
    private String phone;
    private String password;
    private EmployeeState state;
    private EmployeePosition role;

    protected EmployeeModel(){}

    @Builder
    public EmployeeModel(String name, String phone, String password, EmployeeState state, EmployeePosition role) {
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.role = role;
        this.password = password;
    }

    protected void on(ComebackedEmployeeEvent event){
        state = EmployeeState.WORK;
    }

    protected void on(LeavedEmployeeEvent event){
        state = EmployeeState.LEAVE;
    }

    @JsonIgnore
    public boolean isLeaved() {
        return state == EmployeeState.LEAVE;
    }

    /**
     * @param requesterInfo
     * # 다른 사원의 정보를 조회할 수 있는가 체크
     */
    @JsonIgnore
    public boolean isReadAble(EmployeeModel requesterInfo) {
        return requesterInfo.role.equals(EmployeePosition.ACCOUNTING_EMPLOYEE) || requesterInfo.phone.equals(phone);
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", role=" + role +
                '}';
    }
}
