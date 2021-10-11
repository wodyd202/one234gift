package com.one234gift.customerhistoryservice.presentation;

import com.one234gift.customerhistoryservice.application.CustomerHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerSearchAPI_Test {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerHistoryService customerHistoryService;

    @Test
    void 해당_고객_변경이력_조회() throws Exception{
        mockMvc.perform(get("/api/customer-history/{customerId}","1"))
                .andExpect(status().isOk());
    }
}
