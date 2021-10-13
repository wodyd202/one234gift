package com.one234gift.orderservice.query;

import com.one234gift.orderservice.APITest;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.orderservice.domain.OrderFixture.aRegisterOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
public class OrderSearchAPI_Test extends APITest {

    @Test
    void 주문목록_보기() throws Exception{
        mockMvc.perform(get("/api/order")
                        .param("page","0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void 주문_상세_보기() throws Exception {
        OrderModel orderModel = registerOrderService.register(aRegisterOrder().build());

        mockMvc.perform(get("/api/order/{orderId}", orderModel.getId()))
                .andExpect(status().isOk());
    }
}
