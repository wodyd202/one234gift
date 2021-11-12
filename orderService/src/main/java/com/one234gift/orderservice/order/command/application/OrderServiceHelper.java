package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.command.application.exception.CustomerNotFoundException;
import com.one234gift.orderservice.order.command.application.external.CustomerRepository;
import com.one234gift.orderservice.order.command.application.external.UserRepository;
import com.one234gift.orderservice.order.command.application.util.ProcessUserIdGetter;
import com.one234gift.orderservice.order.domain.exception.OrderNotFoundException;
import com.one234gift.orderservice.order.command.application.exception.SalesUserNotFoundException;
import com.one234gift.orderservice.order.domain.Order;
import com.one234gift.orderservice.order.domain.value.CustomerInfo;
import com.one234gift.orderservice.order.domain.value.SalesUser;

public class OrderServiceHelper {
    public static SalesUser findUser(UserRepository userRepository, ProcessUserIdGetter userIdGetter) {
        return userRepository.findUser(userIdGetter.get()).orElseThrow(SalesUserNotFoundException::new);
    }

    public static CustomerInfo findById(CustomerRepository customerRepository, long customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public static Order findByIdAndUserId(OrderRepository orderRepository, long orderId, String userId) {
        return orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(OrderNotFoundException::new);
    }

    public static Order findById(OrderRepository orderRepository, long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }
}
