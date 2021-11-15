package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import com.one234gift.employeeService.employee.command.event.JoinedEmployeeEvent;
import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.domain.exception.AlreadyExistEmployeeException;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * 사원 등록 서비스
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
@Retryable(maxAttempts = 3, value = SQLException.class, backoff = @Backoff(delay = 500))
public class JoinEmployeeService {
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param joinEmployee
     * # 사원 등록
     */
    public EmployeeModel join(JoinEmployee joinEmployee) {
        Employee employee = employeeMapper.mapFrom(joinEmployee, passwordEncoder);
        verifyNotExistEmployee(employee);
        employeeRepository.save(employee);
        EmployeeModel employeeModel = employee.toModel();
        log.info("save employee into database : {}", employeeModel);

        // publish event
        applicationEventPublisher.publishEvent(JoinedEmployeeEvent.builder()
                                .name(employeeModel.getName())
                                .phone(employeeModel.getPhone())
                                .password(employeeModel.getPassword())
                                .position(employeeModel.getPosition())
                                .build());
        return employeeModel;
    }

    /**
     * @param employee
     * # 사원 존재 여부 확인
     */
    private void verifyNotExistEmployee(Employee employee){
        if(employeeRepository.existsByPhone(employee.getPhone())){
            throw new AlreadyExistEmployeeException();
        }
    }
}
