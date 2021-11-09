package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.command.application.event.OrderedEvent;
import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.orderservice.command.application.OrderServiceHelper.findById;
import static com.one234gift.orderservice.command.application.OrderServiceHelper.findUser;

/**
 * 주문 등록 서비스
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegisterOrderService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param registerOrder
     * # 주문 등록
     */
    public OrderModel register(RegisterOrder registerOrder) {
        // 해당 영업자 조회
        SalesUser salesUser = findUser(userRepository);

        // 고객 정보 조회
        CustomerInfo customerInfo = findById(customerRepository, registerOrder.getCustomerId());

        Order order = Order.register(customerInfo, salesUser, registerOrder);
        order.place();
        orderRepository.save(order);
        OrderModel orderModel = order.toModel();

        log.info("save order : {}", orderModel);

        applicationEventPublisher.publishEvent(new OrderedEvent(orderModel));
        return orderModel;
    }

}
