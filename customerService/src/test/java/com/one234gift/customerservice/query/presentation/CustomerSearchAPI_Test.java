package com.one234gift.customerservice.query.presentation;

import com.one234gift.customerservice.APITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000", roles = {"SalesUser"})
public class CustomerSearchAPI_Test extends APITest {

    @Test
    void existByCustomerId() throws Exception {
        mockMvc.perform(get("/api/customer/{customerId}/exist",1))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/api/customer")
                .param("size", "10")
                .param("page", "0"))
            .andExpect(status().isOk());
    }

    @Test
    void searchForBusinessName() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("businessName", "businessName")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    void searchForLocation() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("location", "location")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    void searchForCategory() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("category", "category")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    void searchForState() throws Exception {
        mockMvc.perform(get("/api/customer")
                        .param("state", "SALE")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk());
    }

}
