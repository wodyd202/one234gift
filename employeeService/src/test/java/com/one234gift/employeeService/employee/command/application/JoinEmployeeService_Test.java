package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import com.one234gift.employeeService.employee.domain.exception.AlreadyExistEmployeeException;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import com.one234gift.employeeService.employee.EmployeeAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.employeeService.employee.EmployeeFixture.aRegisterEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 사용자 등록 테스트
 */
public class JoinEmployeeService_Test extends EmployeeAPITest {
    @Autowired
    JoinEmployeeService joinEmployeeService;

    @Test
    void 경리_담당자_입사(){
        // given
        JoinEmployee joinEmployee = aRegisterEmployee().phone("010-0000-0000").position(EmployeePosition.ACCOUNTING_EMPLOYEE).build();

        // when
        EmployeeModel employeeModel = joinEmployeeService.join(joinEmployee);

        // then
        assertEquals(employeeModel.getRole(), EmployeePosition.ACCOUNTING_EMPLOYEE);
    }

    @Test
    void 영업_담당자_입사(){
        // given
        JoinEmployee joinEmployee = aRegisterEmployee().phone("010-0000-0001").position(EmployeePosition.SALES_EMPLOYEE).build();

        // when
        EmployeeModel employeeModel = joinEmployeeService.join(joinEmployee);

        // then
        assertEquals(employeeModel.getRole(), EmployeePosition.SALES_EMPLOYEE);
    }

    @Test
    void 입사_처리시_중복된_휴대폰번호가_있으면_에러발생(){
        // given
        JoinEmployee joinEmployee = aRegisterEmployee().phone("010-0000-0002").position(EmployeePosition.ACCOUNTING_EMPLOYEE).build();
        joinEmployeeService.join(joinEmployee);

        // when
        assertThrows(AlreadyExistEmployeeException.class,()->{
            joinEmployeeService.join(joinEmployee);
        });
    }
}
