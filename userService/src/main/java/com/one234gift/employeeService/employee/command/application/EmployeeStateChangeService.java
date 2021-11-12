package com.one234gift.employeeService.employee.command.application;

import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.Phone;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static com.one234gift.employeeService.employee.command.application.EmployeeServiceHelper.findByPhone;

/**
 * 사원 상태 변경 서비스
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
@Retryable(maxAttempts = 3, value = SQLException.class, backoff = @Backoff(delay = 500))
public class EmployeeStateChangeService {
    private EmployeeRepository employeeRepository;

    /**
     * @param phone
     * # 재영입
     */
    public EmployeeModel comeback(Phone phone) {
        Employee employee = findByPhone(employeeRepository, phone);
        employee.comeBack();
        employeeRepository.save(employee);
        EmployeeModel employeeModel = employee.toModel();
        log.info("comback employee : {}", employeeModel);
        return employeeModel;
    }

    /**
     * @param phone
     * # 퇴사
     */
    public EmployeeModel leave(Phone phone) {
        Employee employee = findByPhone(employeeRepository, phone);
        employee.leave();
        employeeRepository.save(employee);
        EmployeeModel employeeModel = employee.toModel();
        log.info("leave employee : {}", employeeModel);
        return employeeModel;
    }
}
