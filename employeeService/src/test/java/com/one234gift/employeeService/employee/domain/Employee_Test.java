package com.one234gift.employeeService.employee.domain;

import com.one234gift.employeeService.employee.command.application.EmployeeMapper;
import com.one234gift.employeeService.employee.domain.value.EmployeeName;
import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import com.one234gift.employeeService.employee.domain.exception.AlreadyLeaveException;
import com.one234gift.employeeService.employee.domain.exception.AlreadyWorkingException;
import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.Phone;
import com.one234gift.employeeService.employee.domain.value.EmployeeState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.one234gift.employeeService.employee.domain.value.EmployeeState.LEAVE;
import static com.one234gift.employeeService.employee.EmployeeFixture.aEmployee;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * 사용자 도메인 테스트
 */
public class Employee_Test {

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "",
            "홍길동123",
            "홍길 동"
    })
    void 사원의_이름은_한글_조합만_허용함(String name){
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new EmployeeName(name);
        });
    }

    @Test
    void 사원의_이름_정상_입력(){
        // when
        EmployeeName name = new EmployeeName("홍길동");

        // then
        assertEquals(name, new EmployeeName("홍길동"));
        assertEquals(name.get(), "홍길동");
    }

    @Test
    void 사원의_휴대폰_정상_입력(){
        // when
        Phone phone = new Phone("010-0000-0000");

        // then
        assertEquals(phone, new Phone("010-0000-0000"));
        assertEquals(phone.get(), "010-0000-0000");
    }

    @Test
    public void 사원의_휴대폰_양식이_올바르지_않으면_에러발생(){
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new Phone("01000000000");
        });
    }

    @Test
    void 사원_생성(){
        // given
        EmployeeMapper employeeMapper = new EmployeeMapper();
        JoinEmployee joinEmployee = JoinEmployee.builder()
                .phone("010-0000-0000")
                .name("이름")
                .position(EmployeePosition.SALES_EMPLOYEE)
                .build();

        // when
        Employee user = employeeMapper.mapFrom(joinEmployee, mock(PasswordEncoder.class));
        EmployeeModel employeeModel = user.toModel();

        //then
        assertEquals(employeeModel.getPhone(), "010-0000-0000");
        assertEquals(employeeModel.getName(), "이름");
        assertEquals(employeeModel.getState(), EmployeeState.WORK);
        assertEquals(employeeModel.getRole(), EmployeePosition.SALES_EMPLOYEE);
    }

    @Test
    void 사원_퇴사_처리(){
        // given
        Employee employee = aEmployee("010-0000-0000");

        // when
        employee.leave();
        EmployeeModel employeeModel = employee.toModel();

        // then
        assertEquals(employeeModel.getState(), LEAVE);
    }

    @Test
    void 이미_퇴사한_사원은_퇴사_처리_할_수_없음(){
        // given
        Employee employee = aEmployee("010-0000-0000");
        employee.leave();

        // when
        assertThrows(AlreadyLeaveException.class, ()->{
            employee.leave();
        });
    }

    // 퇴사 후 다시 복귀한 경우
    @Test
    void 이미_퇴사한_사원을_재영입_할_수_있음(){
        // given
        Employee employee = aEmployee("000-0000-0000");
        employee.leave();

        // when
        employee.comeBack();
        EmployeeModel employeeModel = employee.toModel();

        // then
        assertEquals(employeeModel.getState(), EmployeeState.WORK);
    }

    @Test
    void 이미_근무중인_사원은_재영입_처리_할_수_없음(){
        // given
        Employee employee = aEmployee("000-0000-0000");
        employee.leave();
        employee.comeBack();

        // when
        assertThrows(AlreadyWorkingException.class, ()->{
            employee.comeBack();
        });
    }
    
}
