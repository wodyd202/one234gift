package com.one234gift.orderservice.order.query;

import com.one234gift.orderservice.OrderAPITest;
import com.one234gift.orderservice.order.command.application.StubUserRepository;
import com.one234gift.orderservice.order.domain.read.OrderModel;
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

    @Test
    void 해당_고객에_대한_주문_보기() throws Exception {
        // when
        mockMvc.perform(get("/api/order/customer/{customerId}",1)
                        .param("page","0")
                        .param("size", "10"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 해당_영업자의_누적_매출_보기() throws Exception {
        // when
        mockMvc.perform(get("/api/order/cumulative-sales/{userId}", "userId")
                        .param("date", "2021-11-01"))

        // then
        .andExpect(status().isOk());
    }

//    @Test
//    void 전체_영업자의_누적_매출_보기() throws Exception {
//        // when
//        mockMvc.perform(get("/api/order/cumulative-sales")
//                        .param("date", "2021-11-01"))
//
//        // then
//        .andExpect(status().isOk());
//    }

}
