package com.one234gift.saleshistoryservice.query;

import com.one234gift.saleshistoryservice.APITest;
import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 영업기록 조회 API 테스트
 */
@WithMockUser
public class SalesHistorySearchAPI_Test extends SalesHistoryAPITest {

    @Test
    void 영업기록_조회() throws Exception{
        // when
        mockMvc.perform(get("/api/customer/1/sales-history")
                        .param("size", "10")
                        .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }
}
