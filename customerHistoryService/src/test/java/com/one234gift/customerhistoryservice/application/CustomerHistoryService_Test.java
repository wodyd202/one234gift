package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.application.model.CustomerHistoryModels;
import com.one234gift.customerhistoryservice.common.Pageable;
import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.customerhistoryservice.CustomerHistoryFixture.aCustomerHistoryEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class CustomerHistoryService_Test {
    @Autowired CustomerHistoryService customerHistoryService;

    @Test
    void 고객_이력_등록(){
        CustomerHistoryEvent customerHistoryEvent = aCustomerHistoryEvent().build();
        CustomerHistoryModel customerHistoryModel = customerHistoryService.save(customerHistoryEvent);
        assertNotNull(customerHistoryModel);
    }

    @Test
    void 고객_이력_조회(){
        customerHistoryService.save(aCustomerHistoryEvent().customerId("2").build());
        CustomerHistoryModels customerHistoryModels = customerHistoryService.findAll("2", new Pageable(0, 10));
        assertEquals(customerHistoryModels.getTotalElement(), 1);
    }
}
