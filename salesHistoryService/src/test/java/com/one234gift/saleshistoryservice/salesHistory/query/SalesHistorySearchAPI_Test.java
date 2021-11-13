package com.one234gift.saleshistoryservice.salesHistory.query;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 영업기록 조회 API 테스트
 */
@WithMockUser
public class SalesHistorySearchAPI_Test extends SalesHistoryAPITest {

    @Test
    void 영업기록_리스트_조회() throws Exception{
        // when
        mockMvc.perform(get("/api/sales-history/customer/1")
                        .param("size", "10")
                        .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 영업기록_단건_조회() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build(), getWriter("000-0000-0000"));

        // when
        mockMvc.perform(get("/api/sales-history/{salesHistoryId}", salesHistoryModel.getId()))

        // then
        .andExpect(status().isOk());
    }
}
