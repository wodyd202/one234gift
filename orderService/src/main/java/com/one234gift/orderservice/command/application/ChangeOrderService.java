package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.model.*;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.orderservice.command.application.OrderServiceHelper.findByIdAndUserId;

@Service
@Transactional
public class ChangeOrderService {
    private OrderRepository orderRepository;

    public ChangeOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel changeDelivery(long orderId, ChangeDelivery delivery, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.changeDelivery(delivery);
        return order.toModel();
    }

    public OrderModel changeQuantity(long orderId, ChangeQuantity quantity, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.changeQuantity(quantity);
        return order.toModel();
    }

    public OrderModel changePurchasePrice(long orderId, ChangePurchasePrice purchasePrice, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.changePurchasePrice(purchasePrice);
        return order.toModel();
    }

    public OrderModel changeSalePrice(long orderId, ChangeSalePrice salePrice, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.changeSalePrice(salePrice);
        return order.toModel();
    }

    public OrderModel changeContent(long orderId, ChangeContent content, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.changeContent(content);
        return order.toModel();
    }
}
