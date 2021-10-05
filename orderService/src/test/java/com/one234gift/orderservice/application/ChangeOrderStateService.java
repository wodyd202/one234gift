package com.one234gift.orderservice.application;

import com.one234gift.orderservice.command.application.OrderRepository;
import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.orderservice.command.application.OrderServiceHelper.findById;

@Service
@Transactional
public class ChangeOrderStateService {
    private OrderRepository orderRepository;

    public ChangeOrderStateService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel refuse(Long orderId) {
        Order order = findById(orderRepository, orderId);
        order.refuse();
        orderRepository.save(order);
        return order.toModel();
    }

    public OrderModel cencel(Long orderId) {
        Order order = findById(orderRepository, orderId);
        order.cancel();
        orderRepository.save(order);
        return order.toModel();
    }

    public OrderModel complate(Long orderId) {
        Order order = findById(orderRepository, orderId);
        order.complate();
        orderRepository.save(order);
        return order.toModel();
    }
}
