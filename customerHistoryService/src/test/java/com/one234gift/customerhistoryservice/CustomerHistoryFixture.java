package com.one234gift.customerhistoryservice;

import com.one234gift.customerhistoryservice.application.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.application.model.CustomerHistoryEvent.CustomerHistoryEventBuilder;

public class CustomerHistoryFixture {
    public static CustomerHistoryEventBuilder aCustomerHistoryEvent(){
        return CustomerHistoryEvent
                .builder()
                .customerId(1)
                .who("manager")
                .payload("payload")
                .type("type");
    }
}
