package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.APITest;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000")
public class RemoveSalesHistoryAPI_Test extends APITest {
    SalesHistoryModel salesHistory;

    @BeforeEach
    void setUp() {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().build();
        this.salesHistory = registerSalesHistoryService.register(salesHistory);
    }

    @Test
    void 영업기록_삭제() throws Exception {
        mockMvc.perform(delete("/api/sales-history/{salesHistoryId}",salesHistory.getId()))
                .andExpect(status().isOk());
    }
}
