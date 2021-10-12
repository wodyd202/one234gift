package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.orderservice.command.application.OrderServiceHelper.findByIdAndUserId;

@Service
@Transactional
public class ChangeOrderStateService {
    private OrderRepository orderRepository;

    public ChangeOrderStateService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel cencel(Long orderId, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.cancel();
        orderRepository.save(order);
        return order.toModel();
    }


    public OrderModel finish(Long orderId, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.finish();
        orderRepository.save(order);
        return order.toModel();
    }
}
