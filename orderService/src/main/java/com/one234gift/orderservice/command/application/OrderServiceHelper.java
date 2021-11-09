package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.orderservice.domain.exception.OrderNotFoundException;
import com.one234gift.orderservice.command.application.exception.SalesUserNotFoundException;
import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;

public class OrderServiceHelper {
    public static SalesUser findUser(UserRepository userRepository) {
        return userRepository.findUser().orElseThrow(SalesUserNotFoundException::new);
    }

    public static CustomerInfo findById(CustomerRepository customerRepository, long customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public static Order findByIdAndUserId(OrderRepository orderRepository, long orderId, String userId) {
        return orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(OrderNotFoundException::new);
    }

    public static Order findById(OrderRepository orderRepository,long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }
}
