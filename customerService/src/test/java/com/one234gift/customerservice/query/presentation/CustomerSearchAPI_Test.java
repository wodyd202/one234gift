package com.one234gift.customerservice.query.presentation;

import com.one234gift.customerservice.APITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000", roles = {"SalesUser"})
public class CustomerSearchAPI_Test extends APITest {

    @Test
    void 내가_담당하고_있는_고객_보기() throws Exception {
        mockMvc.perform(get("/api/customer/responsible")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    void 해당_고객이_존재하는지_확인() throws Exception {
        mockMvc.perform(get("/api/customer/{customerId}/exist",1))
                .andExpect(status().isOk());
    }

    @Test
    void 전체_고객_보기() throws Exception {
        mockMvc.perform(get("/api/customer")
                .param("size", "10")
                .param("page", "0"))
            .andExpect(status().isOk());
    }

    @Test
    void 상호명으로_검색() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("businessName", "businessName")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    void 지역으로_검색() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("location", "location")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    void 분류로_검색() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("category", "category")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    void 상태로_검색() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("state", "SALE")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

}
