package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.command.application.event.OrderedEvent;
import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.orderservice.command.application.OrderServiceHelper.findById;
import static com.one234gift.orderservice.command.application.OrderServiceHelper.findUser;

@Service
@Transactional
@Setter
public class RegisterOrderService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    public RegisterOrderService(UserRepository userRepository,
                                CustomerRepository customerRepository,
                                OrderRepository orderRepository,
                                ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public OrderModel register(RegisterOrder registerOrder) {
        CustomerInfo customerInfo = findById(customerRepository, registerOrder.getCustomerId());
        SalesUser salesUser = findUser(userRepository);

        Order order = Order.register(customerInfo, salesUser, registerOrder);
        order.place();
        orderRepository.save(order);
        OrderModel orderModel = order.toModel();
        applicationEventPublisher.publishEvent(new OrderedEvent(orderModel));
        return orderModel;
    }
}
