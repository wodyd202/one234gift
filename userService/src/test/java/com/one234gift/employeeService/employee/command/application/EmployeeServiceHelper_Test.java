package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.EmployeeAPITest;
import com.one234gift.employeeService.employee.domain.exception.EmployeeNotFoundException;
import com.one234gift.employeeService.employee.domain.value.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.one234gift.employeeService.employee.EmployeeFixture.aEmployee;
import static com.one234gift.employeeService.employee.command.application.EmployeeServiceHelper.findByPhone;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class EmployeeServiceHelper_Test extends EmployeeAPITest {

    @Test
    public void 해당_사원이_존재하지_않으면_에러_발생(){
        Assertions.assertThrows(EmployeeNotFoundException.class, ()->{
            findByPhone(employeeRepository, new Phone("000-0000-0000"));
        });
    }

    @Test
    public void 해당_사원이_존재하면_정상적으로_리턴(){
        employeeRepository.save(aEmployee("000-0000-1234"));
        assertNotNull(findByPhone(employeeRepository, new Phone("000-0000-1234")));
    }
}
