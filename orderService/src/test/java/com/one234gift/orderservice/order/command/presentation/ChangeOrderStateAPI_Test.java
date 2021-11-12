package com.one234gift.orderservice.order.command.presentation;

import com.one234gift.orderservice.OrderAPITest;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.orderservice.OrderFixture.aOrder;
import static com.one234gift.orderservice.OrderFixture.aSalesUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 주문 상태 변경 API 테스트
 */
@WithMockUser(username = "000-0000-0000")
public class ChangeOrderStateAPI_Test extends OrderAPITest {

    @Test
    void 주문_취소() throws Exception {
        // given
        OrderModel order = newOrder(aOrder(aSalesUser("000-0000-0000")));

        // when
        mockMvc.perform(delete("/api/order/{orderId}", order.getId()))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 주문_완료_처리() throws Exception {
        // given
        OrderModel order = newOrder(aOrder(aSalesUser("000-0000-0000")));

        // when
        mockMvc.perform(put("/api/order/{orderId}", order.getId()))

        // then
        .andExpect(status().isOk());
    }
}
