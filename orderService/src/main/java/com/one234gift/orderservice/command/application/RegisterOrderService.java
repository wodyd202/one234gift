package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.orderservice.command.application.OrderServiceHelper.findById;
import static com.one234gift.orderservice.command.application.OrderServiceHelper.findUser;

@Service
@Transactional
public class RegisterOrderService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    public RegisterOrderService(UserRepository userRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public OrderModel register(RegisterOrder registerOrder) {
        SalesUser salesUser = findUser(userRepository);
        CustomerInfo customerInfo = findById(customerRepository, registerOrder.getCustomerId());
        Order order = Order.register(customerInfo, salesUser, registerOrder);
        order.place();
        orderRepository.save(order);
        return order.toModel();
    }
}
