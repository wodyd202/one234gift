package com.one234gift.orderservice.command.presentation;

import com.one234gift.orderservice.APITest;
import com.one234gift.orderservice.command.application.ChangeOrderStateService;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.orderservice.domain.OrderFixture.aRegisterOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000")
public class ChangeOrderStateAPI_Test extends APITest {
    @Autowired
    ChangeOrderStateService changeOrderStateService;

    OrderModel order;

    @Override
    public void init(){
        order = registerOrderService.register(aRegisterOrder().build());
    }

    @Test
    void 주문_취소() throws Exception {
        mockMvc.perform(delete("/api/order/{orderId}", order.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void 주문_완료_처리() throws Exception {
        mockMvc.perform(put("/api/order/{orderId}", order.getId()))
                .andExpect(status().isOk());
    }
}
