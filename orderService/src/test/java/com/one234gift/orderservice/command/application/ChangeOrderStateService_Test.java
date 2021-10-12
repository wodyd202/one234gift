package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.orderservice.domain.OrderFixture.aOrder;
import static com.one234gift.orderservice.domain.value.OrderState.CENCEL;
import static com.one234gift.orderservice.domain.value.OrderState.FINISH;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ChangeOrderStateService_Test {
    @Autowired OrderRepository orderRepository;
    @Autowired ChangeOrderStateService changeOrderStateService;

    OrderModel order;

    @BeforeEach
    void setUp() {
        Order order = aOrder();
        orderRepository.save(order);
        this.order = order.toModel();
    }

    @Test
    void 주문_취소(){
        OrderModel orderModel = changeOrderStateService.cencel(order.getId(), "000-0000-0000");

        assertEquals(orderModel.getState(), CENCEL);
    }

    @Test
    void 주문_완료_처리(){
        OrderModel orderModel = changeOrderStateService.finish(order.getId(), "000-0000-0000");

        assertEquals(orderModel.getState(), FINISH);
    }
}
