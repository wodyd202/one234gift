package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.salesHistory.command.application.exception.EmployeeNotFoundException;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Employee;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.EmployeeRepository;
import com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;

public class SalesHistoryServiceHelper {
    public static SalesHistory getSalesHistory(SalesHistoryRepository salesHistoryRepository, SalesHistoryId id) {
        return salesHistoryRepository.findById(id).orElseThrow(SalesHistoryNotFoundException::new);
    }

    public static Employee getEmployee(EmployeeRepository employeeRepository, Writer writer) {
        return employeeRepository.getEmployee(writer.getPhone()).orElseThrow(EmployeeNotFoundException::new);
    }
}
