package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.application.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import org.springframework.stereotype.Component;

@Component
public class CustomerHistoryMapper {

    public CustomerHistory mapFrom(CustomerHistoryEvent customerHistoryEvent) {
        return CustomerHistory.builder()
                .customerId(customerHistoryEvent.getCustomerId())
                .type(customerHistoryEvent.getType())
                .payload(customerHistoryEvent.getPayload())
                .who(customerHistoryEvent.getWho())
                .build();
    }
}
