package com.one234gift.saleshistoryservice.command;

import com.one234gift.saleshistoryservice.command.application.RegisterSalesHistoryService;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class RegisterSalesHistoryService_Test {
    @Autowired
    RegisterSalesHistoryService registerSalesHistoryService;

    @BeforeEach
    void setUp() {
        registerSalesHistoryService.setUserRepository(new StubUserRepository());
        registerSalesHistoryService.setCustomerRepository(new StubCustomerRepository());
    }

    @Test
    void 영업기록_생성(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().customerId(1L).build();
        SalesHistoryModel salesHistoryModel = registerSalesHistoryService.register(registerSalesHistory);
        assertNotNull(salesHistoryModel);
    }
}
