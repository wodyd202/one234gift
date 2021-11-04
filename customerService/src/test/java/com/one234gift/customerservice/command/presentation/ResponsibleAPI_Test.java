package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.CustomerAPITest;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000", roles = {"SalesUser"})
public class ResponsibleAPI_Test extends CustomerAPITest {

    @Test
    void 고객담당_등록() throws Exception {
        // given
        CustomerModel customer = registerCustomer(aRegisterCustomer().build());

        // when
        mockMvc.perform(post("/api/customer/{customerId}/responsible", customer.getId()))

        // then
        .andExpect(status().isOk());
    }
}
