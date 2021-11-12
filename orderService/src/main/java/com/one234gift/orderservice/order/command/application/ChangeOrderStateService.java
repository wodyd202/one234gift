package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.domain.Order;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.orderservice.order.command.application.OrderServiceHelper.findByIdAndUserId;

/**
 * 주문 상태 변경 서비스
 */
@Service
@Transactional
@AllArgsConstructor
public class ChangeOrderStateService {
    private OrderRepository orderRepository;

    /**
     * @param orderId
     * @param userId
     * # 주문 취소
     */
    public OrderModel cencel(Long orderId, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.cancel();
        orderRepository.save(order);
        return order.toModel();
    }

    /**
     * @param orderId
     * @param userId
     * # 주문 최종 완료(매출 계산)
     */
    public OrderModel finish(Long orderId, String userId) {
        Order order = findByIdAndUserId(orderRepository, orderId, userId);
        order.finish();
        orderRepository.save(order);
        return order.toModel();
    }
}
