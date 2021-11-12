package com.one234gift.employeeService.employee.command.infrastructure;

import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.domain.value.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAEmployeeRepository extends JpaRepository<Employee, Phone> {
}
