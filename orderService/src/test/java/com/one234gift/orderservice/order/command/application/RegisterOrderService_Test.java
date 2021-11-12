package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.command.application.model.RegisterOrder;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.orderservice.OrderFixture.aRegisterOrder;
import static org.junit.Assert.assertNotNull;

/**
 * 주문 등록 테스트
 */
@SpringBootTest
public class RegisterOrderService_Test {
    @Autowired RegisterOrderService registerOrderService;
    @Autowired StubUserRepository stubUserRepository;

    @Test
    void 주문_생성(){
        // given
        stubUserRepository.save("000-0000-0000");
        RegisterOrder registerOrder = aRegisterOrder().build();

        // when
        OrderModel orderModel = registerOrderService.register(registerOrder);

        // then
        assertNotNull(orderModel);
    }
}
