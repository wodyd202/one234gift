package com.one234gift.employeeService.employee.query.infrastructure;

import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.EmployeeState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RedisQueryEmployeeRepository_Test {
    @Autowired
    RedisQueryEmployeeRepository redisQueryEmployeeRepository;

    @Test
    void redis에_사원정보_저장(){
        EmployeeModel employeeModel = EmployeeModel.builder()
                .password("password")
                .role(EmployeePosition.SALES_EMPLOYEE)
                .state(EmployeeState.LEAVE)
                .phone("phone")
                .name("name")
                .build();
        redisQueryEmployeeRepository.save(employeeModel);
        employeeModel = redisQueryEmployeeRepository.findByPhone("phone").get();
        assertEquals(employeeModel.getName(), "name");
        assertEquals(employeeModel.getPassword(), "password");
        assertEquals(employeeModel.getState(), EmployeeState.LEAVE);
        assertEquals(employeeModel.getPhone(), "phone");
        assertEquals(employeeModel.getPosition(), EmployeePosition.SALES_EMPLOYEE);
    }
}
