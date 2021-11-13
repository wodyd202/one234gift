package com.one234gift.saleshistoryservice.salesHistory.command.presentation;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 영업기록 삭제 API 테스트
 */
@WithMockUser(username = "000-0000-0000")
public class RemoveSalesHistoryAPI_Test extends SalesHistoryAPITest {

    @Test
    void 영업기록_삭제() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("000-0000-0000"));

        // when
        mockMvc.perform(delete("/api/sales-history/{salesHistoryId}",salesHistoryModel.getId()))

        // then
        .andExpect(status().isOk());
    }
}
