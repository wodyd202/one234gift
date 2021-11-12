package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.command.application.exception.UserNotFoundException;
import com.one234gift.saleshistoryservice.command.application.external.Employee;
import com.one234gift.saleshistoryservice.command.application.external.EmployeeRepository;
import com.one234gift.saleshistoryservice.command.application.util.ProcessUserIdGetter;
import com.one234gift.saleshistoryservice.domain.SalesHistory;

public class SalesHistoryServiceHelper {
    public static SalesHistory findSalesHistory(SalesHistoryRepository salesHistoryRepository, Long id, String userId) {
        return salesHistoryRepository.findByIdAndUserId(id, userId).orElseThrow(SalesHistoryNotFoundException::new);
    }

    public static boolean existSalesHistory(SalesHistoryRepository salesHistoryRepository, Long id, String userId){
        return salesHistoryRepository.existByIdAndUserId(id, userId);
    }

    public static Employee getEmployee(EmployeeRepository userRepository, ProcessUserIdGetter userIdGetter) {
        return userRepository.getEmployee(userIdGetter.get()).orElseThrow(UserNotFoundException::new);
    }
}
