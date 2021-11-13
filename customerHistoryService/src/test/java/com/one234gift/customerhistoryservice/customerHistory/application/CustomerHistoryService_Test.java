package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.application.model.CustomerHistoryModels;
import com.one234gift.customerhistoryservice.application.model.Pageable;
import com.one234gift.customerhistoryservice.application.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.customerhistoryservice.CustomerHistoryFixture.aCustomerHistoryEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 고객 이력 서비스 테스트
 */
@SpringBootTest
public class CustomerHistoryService_Test {
    @Autowired CustomerHistoryService customerHistoryService;

    @Test
    void 고객_이력_등록(){
        // given
        CustomerHistoryEvent customerHistoryEvent = aCustomerHistoryEvent().build();

        // when
        CustomerHistoryModel customerHistoryModel = customerHistoryService.save(customerHistoryEvent);

        // then
        assertNotNull(customerHistoryModel);
    }

    @Test
    void 고객_이력_조회(){
        // given
        customerHistoryService.save(aCustomerHistoryEvent().customerId(2).build());

        // when
        CustomerHistoryModels customerHistoryModels = customerHistoryService.getCustomerHistorys(2, new Pageable(0, 10));

        // then
        assertEquals(customerHistoryModels.getTotalElement(), 1);
    }
}
