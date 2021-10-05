package com.one234gift.orderservice.application;

import com.one234gift.orderservice.command.application.OrderRepository;
import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.orderservice.domain.OrderFixture.aOrder;
import static com.one234gift.orderservice.domain.value.OrderState.*;
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
    void 승인_거절(){
        OrderModel orderModel = changeOrderStateService.refuse(order.getId());
        assertEquals(orderModel.getState(), REFUSE);
    }

    @Test
    void 주문_취소(){
        OrderModel orderModel = changeOrderStateService.cencel(order.getId());
        assertEquals(orderModel.getState(), CENCEL);
    }

    @Test
    void 주문_승인(){
        OrderModel orderModel = changeOrderStateService.complate(order.getId());
        assertEquals(orderModel.getState(), COMPLATE);
    }

}
