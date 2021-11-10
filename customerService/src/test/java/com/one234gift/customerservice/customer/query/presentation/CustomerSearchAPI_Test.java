package com.one234gift.customerservice.customer.query.presentation;

import com.one234gift.customerservice.customer.CustomerAPITest;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.customerservice.customer.CustomerFixture.aRegisterCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000", roles = {"SalesUser"})
public class CustomerSearchAPI_Test extends CustomerAPITest {

    @Test
    void 해당_고객_보기() throws Exception {
        // given
        CustomerModel customerModel = registerCustomer(aRegisterCustomer().build());

        // when
        mockMvc.perform(get("/api/customer/{customerId}", customerModel.getId()))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 내가_담당하고_있는_고객_보기() throws Exception {
        // given
        mockMvc.perform(get("/api/customer/responsible")
                        .param("size", "10")
                        .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 내가_담당하고_있는_고객_중_지역으로_검색() throws Exception {
        // given
        mockMvc.perform(get("/api/customer/responsible")
                        .param("location", "location")
                        .param("size", "10")
                        .param("page", "0"))
        // then
        .andExpect(status().isOk());
    }

    @Test
    void 해당_고객이_존재하는지_확인() throws Exception {
        // given
        mockMvc.perform(get("/api/customer/{customerId}/exist",1))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 전체_고객_보기() throws Exception {
        // given
        mockMvc.perform(get("/api/customer")
                .param("size", "10")
                .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 상호명으로_검색() throws Exception {
        // when
        mockMvc.perform(get("/api/customer")
                        .param("businessName", "고객명")
                        .param("size", "10")
                        .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 지역으로_검색() throws Exception {
        // when
        mockMvc.perform(get("/api/customer")
                        .param("location", "location")
                        .param("size", "10")
                        .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 분류로_검색() throws Exception {
        // when
        mockMvc.perform(get("/api/customer")
                        .param("category", "분류")
                        .param("size", "10")
                        .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 상태로_검색() throws Exception {
        // when
        mockMvc.perform(get("/api/customer")
                        .param("state", "SALE")
                        .param("size", "10")
                        .param("page", "0"))

        // then
        .andExpect(status().isOk());
    }
}
