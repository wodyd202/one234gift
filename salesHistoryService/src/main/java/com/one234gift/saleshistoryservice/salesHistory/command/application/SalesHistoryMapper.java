package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Customer;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Employee;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.HistoryContent;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SalesHistoryMapper {
    public SalesHistory mapFrom(RegisterSalesHistory registerSalesHistory, Customer customer, Employee employee) {
        return SalesHistory.builder()
                .id(createSalesHistoryId())
                .customer(customer)
                .content(new HistoryContent(registerSalesHistory.getContent()))
                .sample(registerSalesHistory.isSample())
                .catalogue(registerSalesHistory.isCatalogue())
                .callReservationDate(registerSalesHistory.getCallReservationDate())
                .reactivity(registerSalesHistory.getReactivity())
                .writer(new Writer(employee.getName(),employee.getPhone()))
                .build();
    }

    private SalesHistoryId createSalesHistoryId() {
        return new SalesHistoryId(UUID.randomUUID().toString());
    }
}
