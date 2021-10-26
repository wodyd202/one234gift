package com.one234gift.customerhistoryservice;

import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent.CustomerHistoryEventBuilder;

public class CustomerHistoryFixture {
    public static CustomerHistoryEventBuilder aCustomerHistoryEvent(){
        return CustomerHistoryEvent
                .builder()
                .customerId("1")
                .manager("manager")
                .payload("payload")
                .type("type");
    }
}
