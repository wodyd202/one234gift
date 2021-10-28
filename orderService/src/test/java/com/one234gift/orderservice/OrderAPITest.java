package com.one234gift.orderservice;

import com.one234gift.orderservice.command.application.OrderRepository;
import com.one234gift.orderservice.command.application.StubUserRepository;
import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderAPITest extends APITest {
    @Autowired OrderRepository orderRepository;

    protected OrderModel newOrder(Order order){
        orderRepository.save(order);
        return order.toModel();
    }
}
