package com.one234gift.orderservice.application;

import com.one234gift.orderservice.command.application.ChangeOrderService;
import com.one234gift.orderservice.command.application.OrderRepository;
import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.model.*;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.orderservice.domain.OrderFixture.aOrder;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ChangeOrderService_Test {
    @Autowired
    ChangeOrderService changeOrderService;
    @Autowired
    OrderRepository orderRepository;

    OrderModel order;

    @BeforeEach
    void setUp() {
        Order order = aOrder();
        orderRepository.save(order);
        this.order = order.toModel();
    }

    @Test
    void 배송지정보_수정(){
        long orderId = order.getId();
        String userId = "000-0000-0000";
        OrderModel orderModel = changeOrderService.changeDelivery(orderId, ChangeDelivery.builder()
                .addressDetail("배송지 주소 변경")
                .build(), userId);
        assertEquals(orderModel.getDelivery(), "배송지 주소 변경");
    }

    @Test
    void 수량_수정(){
        long orderId = order.getId();
        String userId = "000-0000-0000";
        OrderModel orderModel = changeOrderService.changeQuantity(orderId, ChangeQuantity.builder()
                .quantity(30L)
                .build(), userId);
        assertEquals(orderModel.getQuantity(), 30L);
    }

    @Test
    void 매입단가_수정(){
        long orderId = order.getId();
        String userId = "000-0000-0000";
        OrderModel orderModel = changeOrderService.changePurchasePrice(orderId, ChangePurchasePrice.builder()
                .price(2000L)
                .build(), userId);
        assertEquals(orderModel.getPurchasePrice(), 2000L);
    }

    @Test
    void 판매단가_수정(){
        long orderId = order.getId();
        String userId = "000-0000-0000";
        OrderModel orderModel = changeOrderService.changeSalePrice(orderId, ChangeSalePrice.builder()
                .price(2000L)
                .build(), userId);
        assertEquals(orderModel.getSalePrice(), 2000L);
    }

    @Test
    void 비고_수정(){
        long orderId = order.getId();
        String userId = "000-0000-0000";
        OrderModel orderModel = changeOrderService.changeContent(orderId, ChangeContent.builder()
                .content("비고 수정")
                .build(), userId);
        assertEquals(orderModel.getContent(), "비고 수정");
    }

}
