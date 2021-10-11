package com.one234gift.customerhistoryservice.domain;

import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CustomerHistory_Test {

    @Test
    void 고객_변경_이력_등록(){
        CustomerHistoryEvent customerHistoryEvent = CustomerHistoryEvent.builder()
                .customerId("1")
                .manager("manager")
                .payload("payload")
                .type("type")
                .build();
        CustomerHistory customerHistory = CustomerHistory.register(customerHistoryEvent);
        CustomerHistoryModel customerHistoryModel = customerHistory.toModel();
        assertEquals(customerHistoryModel.getCustomerId(), "1");
        assertEquals(customerHistoryModel.getManager(), "manager");
        assertEquals(customerHistoryModel.getPayload(), "payload");
        assertEquals(customerHistoryModel.getType(), "type");
    }
}
