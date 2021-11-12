package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.command.application.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.command.application.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.command.application.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.junit.Assert.*;

/**
 * 영업 기록 수정 테스트
 */
public class ChangeSalesHistoryService_Test extends SalesHistoryAPITest {
    @Autowired ChangeSalesHistoryService changeSalesHistoryService;

    @BeforeEach
    void setUp(){
        // 사용자 생성
        newUser("000-0000-0000");
    }

    @Test
    void 자신의_영업_기록만_수정_가능(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        assertThrows(SalesHistoryNotFoundException.class,()->{
            changeSalesHistoryService.changeSample(salesHistoryModel.getId(), "other");
        });
    }

    @Test
    void 샘플_유무_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        salesHistoryModel = changeSalesHistoryService.changeSample(salesHistoryModel.getId(), "000-0000-0000");

        // then
        assertTrue(salesHistoryModel.isSample());
    }

    @Test
    void 카탈로그_유무_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        salesHistoryModel = changeSalesHistoryService.changeCatalogue(salesHistoryModel.getId(), "000-0000-0000");

        // then
        assertTrue(salesHistoryModel.isCatalogue());
    }

    @Test
    void 내용_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        salesHistoryModel = changeSalesHistoryService.changeContent(salesHistoryModel.getId(), ChangeSalesHistoryContent.builder()
                        .content("내용 변경")
                .build(),"000-0000-0000");

        // then
        assertEquals(salesHistoryModel.getContent(), "내용 변경");
    }

    @Test
    void 예약콜_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        salesHistoryModel = changeSalesHistoryService.changeCallReservationDate(salesHistoryModel.getId(), ChangeCallReservationDate.builder()
                        .callReservationDate(LocalDate.now().plusDays(2))
                .build(),"000-0000-0000");

        // then
        assertEquals(salesHistoryModel.getCallReservationDate(), LocalDate.now().plusDays(2));
    }

    @Test
    void 반응도_변경(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        salesHistoryModel = changeSalesHistoryService.changeReactivity(salesHistoryModel.getId(), ChangeCustomerReactivity.builder()
                        .reactivity(CustomerReactivity.FIVE)
                .build(),"000-0000-0000");

        // then
        assertEquals(salesHistoryModel.getReactivity(), CustomerReactivity.FIVE);
    }
}
