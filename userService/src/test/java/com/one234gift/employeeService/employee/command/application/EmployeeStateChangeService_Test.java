package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.Phone;
import com.one234gift.employeeService.employee.domain.value.EmployeeState;
import com.one234gift.employeeService.employee.EmployeeAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.employeeService.employee.command.application.EmployeeServiceHelper.findByPhone;
import static com.one234gift.employeeService.employee.domain.value.EmployeeState.LEAVE;
import static com.one234gift.employeeService.employee.EmployeeFixture.aEmployee;
import static com.one234gift.employeeService.employee.EmployeeFixture.leavedEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 사용자 재영입 테스트
 */
public class EmployeeStateChangeService_Test extends EmployeeAPITest {
    @Autowired
    EmployeeStateChangeService employeeStateChangeService;

    @Test
    void 이미_퇴사한_사원을_재영입_할_수_있음() {
        // given
        persistEmployee(leavedEmployee("010-0000-0004"));

        // when
        Phone phone = new Phone("010-0000-0004");
        employeeStateChangeService.comeback(phone);
        EmployeeModel employeeModel = findByPhone(employeeRepository, phone).toModel();

        // then
        assertEquals(employeeModel.getState(), EmployeeState.WORK);
    }

    @Test
    void 회사에_근무중인_사원은_퇴사할_수_있음(){
        // given
        persistEmployee(aEmployee("000-0000-0005"));

        // when
        Phone phone = new Phone("000-0000-0005");
        employeeStateChangeService.leave(phone);
        EmployeeModel employeeModel = findByPhone(employeeRepository, phone).toModel();

        // then
        assertEquals(employeeModel.getState(), LEAVE);
    }
}
