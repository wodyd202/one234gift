package com.one234gift.orderservice.query;

import com.one234gift.orderservice.OrderAPITest;
import com.one234gift.orderservice.command.application.StubUserRepository;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.orderservice.OrderFixture.aRegisterOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
public class OrderSearchAPI_Test extends OrderAPITest {
    @Autowired
    StubUserRepository stubUserRepository;

    @Override
    public void init() {
        stubUserRepository.save("000-0000-0000");
    }

    @Test
    void 주문목록_보기() throws Exception{
        // when
        mockMvc.perform(get("/api/order")
                        .param("page","0")
                        .param("size", "10"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 주문_상세_보기() throws Exception {
        // given
        OrderModel orderModel = registerOrderService.register(aRegisterOrder().build());

        // when
        mockMvc.perform(get("/api/order/{orderId}", orderModel.getId()))

        // then
        .andExpect(status().isOk());
    }
}
