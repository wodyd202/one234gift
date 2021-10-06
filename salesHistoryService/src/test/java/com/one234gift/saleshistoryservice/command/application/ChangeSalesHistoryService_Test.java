package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.StubUserRepository;
import com.one234gift.saleshistoryservice.domain.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ChangeSalesHistoryService_Test {
    @Autowired
    ChangeSalesHistoryService changeSalesHistoryService;
    @Autowired
    RegisterSalesHistoryService registerSalesHistoryService;
    SalesHistoryModel salesHistory;


    @BeforeEach
    void setUp() {
        registerSalesHistoryService.setUserRepository(new StubUserRepository());

        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        this.salesHistory = registerSalesHistoryService.register(registerSalesHistory);
    }

    @Test
    void 샘플_유무_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeSample(salesHistory.getId(), "000-0000-0000");
        assertTrue(salesHistoryModel.isSample());
    }

    @Test
    void 카탈로그_유무_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCatalogue(salesHistory.getId(), "000-0000-0000");
        assertTrue(salesHistoryModel.isCatalogue());
    }

    @Test
    void 내용_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeContent(salesHistory.getId(), ChangeSalesHistoryContent.builder()
                        .content("내용 변경")
                .build(),"000-0000-0000");
        assertEquals(salesHistoryModel.getContent(), "내용 변경");
    }

    @Test
    void 예약콜_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCallReservationDate(salesHistory.getId(), ChangeCallReservationDate.builder()
                        .callReservationDate(LocalDate.now().plusDays(2))
                .build(),"000-0000-0000");
        assertEquals(salesHistoryModel.getCallReservationDate(), LocalDate.now().plusDays(2));
    }

    @Test
    void 반응도_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeReactivity(salesHistory.getId(), ChangeCustomerReactivity.builder()
                        .reactivity(CustomerReactivity.FIVE)
                .build(),"000-0000-0000");
        assertEquals(salesHistoryModel.getReactivity(), CustomerReactivity.FIVE);
    }
}
