package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 영업기록 삭제 API 테스트
 */
@WithMockUser(username = "000-0000-0000")
public class RemoveSalesHistoryAPI_Test extends SalesHistoryAPITest {

    @BeforeEach
    void setUp(){
        newUser("000-0000-0000");
    }

    @Test
    void 영업기록_삭제() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        mockMvc.perform(delete("/api/sales-history/{salesHistoryId}",salesHistoryModel.getId()))

        // then
        .andExpect(status().isOk());
    }
}
