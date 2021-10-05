package com.one234gift.orderservice.application;

import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.orderservice.domain.OrderFixture.aRegisterOrder;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class RegisterOrderService_Test {
    @Autowired RegisterOrderService registerOrderService;

    @Test
    void 주문_생성(){
        long customerId = 1L;
        RegisterOrder registerOrder = aRegisterOrder().build();
        OrderModel orderModel = registerOrderService.register(customerId, registerOrder);
        assertNotNull(orderModel);
    }
}
