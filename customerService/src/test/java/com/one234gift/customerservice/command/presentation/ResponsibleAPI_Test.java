package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.APITest;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000", roles = {"SalesUser"})
public class ResponsibleAPI_Test extends APITest {

    @Test
    void 고객담당_등록() throws Exception {
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());

        mockMvc.perform(post("/api/customer/{customerId}/responsible", customer.getId()))
                .andExpect(status().isOk());
    }
}
