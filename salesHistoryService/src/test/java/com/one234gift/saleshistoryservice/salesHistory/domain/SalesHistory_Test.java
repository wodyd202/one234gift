package com.one234gift.saleshistoryservice.salesHistory.domain;

import com.one234gift.saleshistoryservice.salesHistory.command.application.SalesHistoryMapper;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Customer;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.CustomerReactivity;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.HistoryContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistoryFixture.*;
import static org.junit.Assert.*;

/**
 * 영업기록 도메인 테스트
 */
public class SalesHistory_Test {
    private SalesHistoryMapper salesHistoryMapper = new SalesHistoryMapper();

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "invalid<>",
            "invalid!@#"
    })
    void 영업내용은_한글_숫자_영어_조합_1자_이상_200자이하만_허용함(String content){
        assertThrows(IllegalArgumentException.class,()->{
            // when
            new HistoryContent(content);
        });
    }

    @Test
    void 영업내용_입력(){
        // when
        HistoryContent historyContent = new HistoryContent("영업 내용");

        // then
        assertEquals(historyContent.get(), "영업 내용");
        assertEquals(historyContent, new HistoryContent("영업 내용"));
    }

    @Test
    void 예약콜_정보는_입력하지_않아도됨(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().callReservationDate(null).build();

        // when
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory, new Customer(), aEmployee().build());
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();

        // then
        assertNull(salesHistoryModel.getCallReservationDate());
    }

    @Test
    void 예약콜은_오늘_이후로_입력해야함(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().callReservationDate(LocalDate.now().minusDays(1)).build();

        assertThrows(IllegalArgumentException.class,()->{
            // when
            salesHistoryMapper.mapFrom(registerSalesHistory,new Customer(), aEmployee().build());
        });
    }

    @Test
    void 영업기록_생성(){
        // given
        RegisterSalesHistory registerSalesHistory = RegisterSalesHistory.builder()
                .customerId(1L)
                .sample(false)
                .catalogue(true)
                .content("영업 내용")
                .callReservationDate(LocalDate.now().plusDays(1))
                .reactivity(CustomerReactivity.FIVE)
                .build();

        // when
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory,new Customer(), aEmployee().build());
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();

        // then
        assertEquals(salesHistoryModel.getCallReservationDate(), LocalDate.now().plusDays(1));
        assertTrue(salesHistoryModel.isCatalogue());
        assertFalse(salesHistoryModel.isSample());
        assertEquals(salesHistoryModel.getContent(), "영업 내용");
        assertEquals(salesHistoryModel.getReactivity(), CustomerReactivity.FIVE);
    }

    @Test
    void 샘플_유무_변경(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().sample(false).build();
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory, new Customer(), aEmployee().phone("변경자").build());

        // when
        salesHistory.changeSample(aWriter("변경자"));

        // then
        assertTrue(salesHistory.toModel().isSample());
    }

    @Test
    void 카탈로그_유무_변경(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().catalogue(false).build();
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory, new Customer(),aEmployee().phone("변경자").build());

        // when
        salesHistory.changeCatalogue(aWriter("변경자"));

        // then
        assertTrue(salesHistory.toModel().isCatalogue());
    }

    @Test
    void 내용_변경(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory, new Customer(), aEmployee().phone("변경자").build());

        // when
        salesHistory.changeContent(new HistoryContent("내용 변경"), aWriter("변경자"));

        // then
        assertEquals(salesHistory.toModel().getContent(), "내용 변경");
    }

    @Test
    void 예약콜_변경(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().callReservationDate(LocalDate.now().plusDays(1)).build();
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory,new Customer(),  aEmployee().phone("변경자").build());

        // when
        salesHistory.changeCallReservationDate(null, new Customer(), aWriter("변경자"));

        // then
        assertNull(salesHistory.toModel().getCallReservationDate());
    }

    @Test
    void 반응도_변경(){
        // given
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory, new Customer(), aEmployee().phone("변경자").build());

        // when
        salesHistory.changeReactivity(CustomerReactivity.FIVE, aWriter("변경자"));

        // then
        assertEquals(salesHistory.toModel().getReactivity(), CustomerReactivity.FIVE);
    }

}
