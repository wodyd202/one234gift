package com.one234gift.employeeService.employee.query.presentation;

import com.one234gift.employeeService.employee.domain.exception.NotAccessEmployeeException;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.query.application.QueryEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사원 조회 API
 */
@RestController
@RequestMapping("api/employee")
public class EmployeeSearchAPI {
    @Autowired private QueryEmployeeService queryEmployeeService;

    /**
     * @param phone
     * # 사원 단건 정보 조회
     */
    @GetMapping("{phone}")
    public ResponseEntity<EmployeeModel> getEmployeeModel(@PathVariable String phone){
        try {
            EmployeeModel employeeModel = queryEmployeeService.getEmployeeModel(phone);
            return ResponseEntity.ok(employeeModel);
        } catch (NotAccessEmployeeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
