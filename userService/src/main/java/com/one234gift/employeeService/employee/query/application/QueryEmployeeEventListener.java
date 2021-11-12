package com.one234gift.employeeService.employee.query.application;

import com.one234gift.employeeService.employee.command.event.AbstractEmployeeEvent;
import com.one234gift.employeeService.employee.command.event.RegisteredEmployeeEvent;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 사원 이벤트 리스너
 */
@Async("queryEmployeeExecutor")
@Component
@Slf4j
@Retryable(maxAttempts = 3, value = RuntimeException.class, backoff = @Backoff(delay = 500))
public class QueryEmployeeEventListener {
    @Autowired private QueryEmployeeRepository queryEmployeeRepository;

    @EventListener
    public void handle(RegisteredEmployeeEvent event){
        EmployeeModel employeeModel = EmployeeModel.builder()
                .name(event.getName())
                .phone(event.getPhone())
                .password(event.getPassword())
                .state(event.getState())
                .role(event.getRole())
                .build();
        queryEmployeeRepository.save(employeeModel);
        log.info("save employee into redis store : {}", employeeModel);
    }

    @EventListener
    public void handle(AbstractEmployeeEvent event) throws Exception {
        Optional<EmployeeModel> employeeModel = queryEmployeeRepository.findByPhone(event.getPhone());
        if(employeeModel.isPresent()){
            EmployeeModel queryEmployeeModel = employeeModel.get();
            invoke(event, queryEmployeeModel);
            queryEmployeeRepository.save(queryEmployeeModel);
            log.info("change employee into redis store : {}", queryEmployeeModel);
        }
    }

    private final String ON = "on";
    private void invoke(AbstractEmployeeEvent event, EmployeeModel queryEmployee) throws Exception {
        Method on = queryEmployee.getClass().getDeclaredMethod(ON, event.getClass());
        on.setAccessible(true);
        on.invoke(queryEmployee, event);
    }
}
