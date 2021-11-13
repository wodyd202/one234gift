package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.salesHistory.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

/**
 * 영업기록 삭제 테스트
 */
public class RemoveSalesHistoryService_Test extends SalesHistoryAPITest {
    @Autowired RemoveSalesHistoryService removeSalesHistoryService;

    @Test
    void 자신의_영업기록만_삭제_가능(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("영업기록삭제"));

        assertThrows(IllegalArgumentException.class, ()->{
            // when
            removeSalesHistoryService.remove(getSalesHistoryId(salesHistoryModel.getId()), getWriter("other"));
        });
    }

    @Test
    void 영업기록_삭제(){
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(),getWriter("000-0000-0000"));

        // when
        removeSalesHistoryService.remove(getSalesHistoryId(salesHistoryModel.getId()), getWriter("000-0000-0000"));

        // then
        Optional<SalesHistoryModel> salesHistory = findSalesHistory(getSalesHistoryId(salesHistoryModel.getId()));
        assertFalse(salesHistory.isPresent());
    }

    @Test
    void 존재하지않는_영업기록(){
        assertThrows(SalesHistoryNotFoundException.class,()->{
            // when
            removeSalesHistoryService.remove(getSalesHistoryId("notExist"), getWriter("000-0000-0000"));
        });
    }

}
