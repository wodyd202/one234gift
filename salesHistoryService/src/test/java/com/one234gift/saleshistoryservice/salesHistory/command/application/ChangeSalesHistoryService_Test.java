package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.CustomerReactivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.junit.Assert.*;

/**
 * 영업 기록 수정 테스트
 */
public class ChangeSalesHistoryService_Test extends SalesHistoryAPITest {
    @Autowired ChangeSalesHistoryService changeSalesHistoryService;

    @Test
    void 자신의_영업_기록만_수정_가능(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("영업기록수정"));

        // when
        assertThrows(IllegalArgumentException.class,()->{
            changeSalesHistoryService.changeSample(getSalesHistoryId(salesHistoryModel.getId()), getWriter("other"));
        });
    }

    @Test
    void 샘플_유무_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("000-0000-0000"));

        // when
        salesHistoryModel = changeSalesHistoryService.changeSample(getSalesHistoryId(salesHistoryModel.getId()), getWriter("000-0000-0000"));

        // then
        assertTrue(salesHistoryModel.isSample());
    }

    @Test
    void 카탈로그_유무_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("000-0000-0000"));

        // when
        salesHistoryModel = changeSalesHistoryService.changeCatalogue(getSalesHistoryId(salesHistoryModel.getId()), getWriter("000-0000-0000"));

        // then
        assertTrue(salesHistoryModel.isCatalogue());
    }

    @Test
    void 내용_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("000-0000-0000"));

        // when
        salesHistoryModel = changeSalesHistoryService.changeContent(getSalesHistoryId(salesHistoryModel.getId()), ChangeSalesHistoryContent.builder()
                        .content("내용 변경")
                .build(),getWriter("000-0000-0000"));

        // then
        assertEquals(salesHistoryModel.getContent(), "내용 변경");
    }

    @Test
    void 예약콜_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("000-0000-0000"));

        // when
        salesHistoryModel = changeSalesHistoryService.changeCallReservationDate(getSalesHistoryId(salesHistoryModel.getId()), ChangeCallReservationDate.builder()
                        .callReservationDate(LocalDate.now().plusDays(2))
                .build(), getWriter("000-0000-0000"));

        // then
        assertEquals(salesHistoryModel.getCallReservationDate(), LocalDate.now().plusDays(2));
    }

    @Test
    void 반응도_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("000-0000-0000"));

        // when
        salesHistoryModel = changeSalesHistoryService.changeReactivity(getSalesHistoryId(salesHistoryModel.getId()), ChangeCustomerReactivity.builder()
                        .reactivity(CustomerReactivity.FIVE)
                .build(),getWriter("000-0000-0000"));

        // then
        assertEquals(salesHistoryModel.getReactivity(), CustomerReactivity.FIVE);
    }

}
