package com.one234gift.saleshistoryservice.command;

import com.one234gift.saleshistoryservice.command.application.RegisterSalesHistoryService;
import com.one234gift.saleshistoryservice.command.application.RemoveSalesHistoryService;
import com.one234gift.saleshistoryservice.command.application.SalesHistoryRepository;
import com.one234gift.saleshistoryservice.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class RemoveSalesHistoryService_Test {
    @Autowired
    RemoveSalesHistoryService removeSalesHistoryService;
    @Autowired
    RegisterSalesHistoryService registerSalesHistoryService;
    @Autowired
    SalesHistoryRepository salesHistoryRepository;

    SalesHistoryModel salesHistory;

    @BeforeEach
    void setUp() {
        registerSalesHistoryService.setUserRepository(new StubUserRepository());

        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        this.salesHistory = registerSalesHistoryService.register(registerSalesHistory);
    }

    @Test
    void 영업기록_삭제(){
        removeSalesHistoryService.remove(salesHistory.getId(), "000-0000-0000");

        assertFalse(salesHistoryRepository.findByIdAndUserId(salesHistory.getId(), "000-0000-0000").isPresent());
    }

    @Test
    void 존재하지않는_영업기록(){
        assertThrows(SalesHistoryNotFoundException.class,()->{
            removeSalesHistoryService.remove(-1L, "000-0000-0000");
        });
    }

}
