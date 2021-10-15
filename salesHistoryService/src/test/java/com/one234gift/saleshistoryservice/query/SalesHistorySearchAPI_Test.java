package com.one234gift.saleshistoryservice.query;

import com.one234gift.saleshistoryservice.APITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
public class SalesHistorySearchAPI_Test extends APITest {

    @Test
    void 영업기록_조회() throws Exception{
        mockMvc.perform(get("/api/customer/1/sales-history")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }
}
