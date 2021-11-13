package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.junit.Assert.assertNotNull;

/**
 * 영업 기록 생성 테스트
 */
@SpringBootTest
public class RegisterSalesHistoryService_Test extends SalesHistoryAPITest {
    @Autowired RegisterSalesHistoryService registerSalesHistoryService;

    @Test
    void 영업기록_생성(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().customerId(1L).build();

        // when
        SalesHistoryModel salesHistoryModel = registerSalesHistoryService.register(registerSalesHistory, getWriter("000-0000-0000"));

        // then
        assertNotNull(salesHistoryModel);
    }
}
