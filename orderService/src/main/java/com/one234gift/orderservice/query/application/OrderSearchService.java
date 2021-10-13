package com.one234gift.orderservice.query.application;

import com.one234gift.orderservice.command.application.exception.OrderNotFoundException;
import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.query.model.OrderModels;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderSearchService {
    private QueryOrderRepository orderRepository;

    public OrderSearchService(QueryOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModels findAll(String userId, Pageable pageable){
        return OrderModels.builder()
                .orderModels(orderRepository.findAll(userId, pageable))
                .totalElement(orderRepository.countAll(userId))
                .pageable(pageable)
                .build();
    }

    public OrderModel findById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }
}
