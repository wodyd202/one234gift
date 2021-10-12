package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.orderservice.domain.OrderFixture.aRegisterOrder;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class RegisterOrderService_Test {
    @Autowired
    RegisterOrderService registerOrderService;

    @BeforeEach
    void setUp() {
        registerOrderService.setUserRepository(new StubUserRepository());
        registerOrderService.setCustomerRepository(new StubCustomerRepository());
    }

    @Test
    void 주문_생성(){
        RegisterOrder registerOrder = aRegisterOrder().build();
        OrderModel orderModel = registerOrderService.register(registerOrder);
        assertNotNull(orderModel);
    }
}
