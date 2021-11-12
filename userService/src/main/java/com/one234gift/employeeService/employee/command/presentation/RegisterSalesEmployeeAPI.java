package com.one234gift.employeeService.employee.command.presentation;

import com.one234gift.employeeService.employee.command.application.JoinEmployeeService;
import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.one234gift.employeeService.common.APIHelper.verifyNotContainsError;


/**
 * 사원 생성 API
 */
@RestController
@RequestMapping("api/employee")
public class RegisterSalesEmployeeAPI {
    @Autowired private JoinEmployeeService joinEmployeeService;

    /**
     * @param joinEmployee
     * @param errors
     * # 사원 생성
     */
    @PostMapping
    public ResponseEntity<EmployeeModel> join(@Valid @RequestBody JoinEmployee joinEmployee, Errors errors){
        verifyNotContainsError(errors);
        EmployeeModel employeeModel = joinEmployeeService.join(joinEmployee);
        return ResponseEntity.ok(employeeModel);
    }
}
