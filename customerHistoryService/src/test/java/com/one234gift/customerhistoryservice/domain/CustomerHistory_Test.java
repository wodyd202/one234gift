package com.one234gift.customerhistoryservice.domain;

import com.one234gift.customerhistoryservice.application.CustomerHistoryMapper;
import com.one234gift.customerhistoryservice.application.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CustomerHistory_Test {

    CustomerHistoryMapper customerHistoryMapper = new CustomerHistoryMapper();

    @Test
    void 고객_변경_이력_생성(){
        CustomerHistoryEvent customerHistoryEvent = CustomerHistoryEvent.builder()
                .customerId(1)
                .who("manager")
                .payload("payload")
                .type("type")
                .build();
        CustomerHistory customerHistory = customerHistoryMapper.mapFrom(customerHistoryEvent);
        CustomerHistoryModel customerHistoryModel = customerHistory.toModel();
        assertEquals(customerHistoryModel.getCustomerId(), 1);
        assertEquals(customerHistoryModel.getWho(), "manager");
        assertEquals(customerHistoryModel.getPayload(), "payload");
        assertEquals(customerHistoryModel.getType(), "type");
    }
}
