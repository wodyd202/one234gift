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
}
