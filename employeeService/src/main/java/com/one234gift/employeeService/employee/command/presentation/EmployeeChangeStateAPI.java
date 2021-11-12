package com.one234gift.employeeService.employee.command.presentation;

import com.one234gift.employeeService.employee.command.application.EmployeeStateChangeService;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 사원 상태 변경 API
 */
@RestController
@RequestMapping("api/employee/{phone}")
public class EmployeeChangeStateAPI {
    @Autowired private EmployeeStateChangeService employeeStateChangeService;

    /**
     * @param phone
     * # 사원 재영입
     */
    @PutMapping("comeback")
    public ResponseEntity<EmployeeModel> comeback(@PathVariable Phone phone){
        EmployeeModel employeeModel = employeeStateChangeService.comeback(phone);
        return ResponseEntity.ok(employeeModel);
    }

    /**
     * @param phone
     * # 사원 퇴사
     */
    @DeleteMapping("leave")
    public ResponseEntity<EmployeeModel> leave(@PathVariable Phone phone){
        EmployeeModel employeeModel = employeeStateChangeService.leave(phone);
        return ResponseEntity.ok(employeeModel);
    }
}
