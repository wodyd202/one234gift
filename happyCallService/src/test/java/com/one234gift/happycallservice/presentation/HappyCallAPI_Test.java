package com.one234gift.happycallservice.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "000-0000-0000")
public class HappyCallAPI_Test {
    @Autowired
    MockMvc mockMvc;

    @Test
    void 금일_예약콜_가져오기() throws Exception{
        // when
        mockMvc.perform(get("/api/happy-call")
                .param("size", "10")
                .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }

}
