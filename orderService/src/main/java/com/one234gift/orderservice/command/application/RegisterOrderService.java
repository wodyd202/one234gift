package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
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
    private CircuitBreakerFactory circuitBreakerFactory;

    public RegisterOrderService(UserRepository userRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public OrderModel register(RegisterOrder registerOrder) {
        CircuitBreaker userAPICircuit = circuitBreakerFactory.create("userAPICircuit");
        CircuitBreaker customerAPICircuit = circuitBreakerFactory.create("customerAPICircuit");

        SalesUser salesUser = userAPICircuit.run(()-> findUser(userRepository), e -> SalesUser.builder().build());
        CustomerInfo customerInfo = customerAPICircuit.run(()->findById(customerRepository, registerOrder.getCustomerId()), e -> CustomerInfo.builder().build());

        Order order = Order.register(customerInfo, salesUser, registerOrder);
        order.place();
        orderRepository.save(order);
        return order.toModel();
    }
}
