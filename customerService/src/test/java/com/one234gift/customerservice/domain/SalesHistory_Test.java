package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.model.ChangeCallReservationDate;
import com.one234gift.customerservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.customerservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.customerservice.domain.model.RegisterSalesHistory;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import com.one234gift.customerservice.domain.value.CustomerReactivity;
import com.one234gift.customerservice.domain.value.HistoryContent;
import com.one234gift.customerservice.domain.value.Manager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static com.one234gift.customerservice.domain.CustomerFixture.aManager;
import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterSalesHistory;
import static org.junit.Assert.*;

public class SalesHistory_Test {

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "invalid<>",
            "invalid!@#"
    })
    void 영업내용은_한글_숫자_영어_조합_1자_이상_200자이하만_허용함(String content){
        assertThrows(IllegalArgumentException.class,()->{
            new HistoryContent(content);
        });
    }

    @Test
    void 영업내용_입력(){
        HistoryContent historyContent = new HistoryContent("영업 내용");
        assertEquals(historyContent.get(), "영업 내용");
        assertEquals(historyContent, new HistoryContent("영업 내용"));
    }

    @Test
    void 예약콜_정보는_입력하지_않아도됨(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().callReservationDate(null).build();

        Long customerId = 1L;
        Manager manager = aManager().build();

        SalesHistory salesHistory = SalesHistory.register(customerId, registerSalesHistory, manager);
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        assertNull(salesHistoryModel.getCallReservationDate());
    }

    @Test
    void 예약콜은_오늘_이후로_입력해야함(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().callReservationDate(LocalDate.now().minusDays(1)).build();

        Long customerId = 1L;
        Manager manager = aManager().build();

        assertThrows(IllegalArgumentException.class,()->{
            SalesHistory.register(customerId, registerSalesHistory, manager);
        });
    }

    @Test
    void 샘플_유무_변경(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().sample(false).build();
        SalesHistory salesHistory = SalesHistory.register(1L, registerSalesHistory, aManager().build());

        salesHistory.changeSample();

        assertTrue(salesHistory.toModel().isSample());
    }

    @Test
    void 카탈로그_유무_변경(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().catalogue(false).build();
        SalesHistory salesHistory = SalesHistory.register(1L, registerSalesHistory, aManager().build());

        salesHistory.changeCatalogue();

        assertTrue(salesHistory.toModel().isCatalogue());
    }

    @Test
    void 내용_변경(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        SalesHistory salesHistory = SalesHistory.register(1L, registerSalesHistory, aManager().build());

        salesHistory.changeContent(ChangeSalesHistoryContent.builder()
                        .content("내용 변경")
                .build());

        assertEquals(salesHistory.toModel().getContent(), "내용 변경");
    }

    @Test
    void 예약콜_변경(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().callReservationDate(LocalDate.now().plusDays(1)).build();
        SalesHistory salesHistory = SalesHistory.register(1L, registerSalesHistory, aManager().build());

        salesHistory.changeCallReservationDate(ChangeCallReservationDate.builder()
                .build());

        assertNull(salesHistory.toModel().getCallReservationDate());
    }

    @Test
    void 반응도_변경(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        SalesHistory salesHistory = SalesHistory.register(1L, registerSalesHistory, aManager().build());

        salesHistory.changeReactivity(ChangeCustomerReactivity.builder()
                        .reactivity(CustomerReactivity.FIVE)
                .build());

        assertEquals(salesHistory.toModel().getReactivity(), CustomerReactivity.FIVE);
    }

}
