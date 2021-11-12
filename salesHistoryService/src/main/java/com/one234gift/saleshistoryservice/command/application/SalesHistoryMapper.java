package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.external.Employee;
import com.one234gift.saleshistoryservice.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.one234gift.saleshistoryservice.domain.value.HistoryContent;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import org.springframework.stereotype.Component;

@Component
public class SalesHistoryMapper {
    public SalesHistory mapFrom(RegisterSalesHistory registerSalesHistory, Employee employee) {
        return SalesHistory.builder()
                .customerId(registerSalesHistory.getCustomerId())
                .content(new HistoryContent(registerSalesHistory.getContent()))
                .sample(registerSalesHistory.isSample())
                .catalogue(registerSalesHistory.isCatalogue())
                .callReservationDate(registerSalesHistory.getCallReservationDate())
                .reactivity(registerSalesHistory.getReactivity())
                .writer(new Writer(employee.getName(),employee.getPhone()))
                .build();
    }
}
