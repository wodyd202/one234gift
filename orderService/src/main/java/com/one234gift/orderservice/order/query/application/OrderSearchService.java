package com.one234gift.orderservice.order.query.application;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.order.domain.exception.OrderNotFoundException;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import com.one234gift.orderservice.order.query.application.model.CalculateSalesModel;
import com.one234gift.orderservice.order.query.application.model.OrderModels;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * 주문 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class OrderSearchService {
    private QueryOrderRepository orderRepository;
    private CalculateSalesRepository calculateSalesRepository;

    /**
     * @param userId
     * @param pageable
     * # 해당 영업 사원의 주문 리스트 조회
     */
    public OrderModels getOrderModelsByUserId(String userId, Pageable pageable){
        return OrderModels.builder()
                .orderModels(orderRepository.findByUserId(userId, pageable))
                .totalElement(orderRepository.countByUserId(userId))
                .pageable(pageable)
                .build();
    }

    /**
     * @param customerId
     * @param pageable
     * # 해당 고객의 주문 리스트 조회
     */
    public OrderModels getOrderModelsByCustomerId(long customerId, Pageable pageable) {
        return OrderModels.builder()
                .orderModels(orderRepository.findAllByCustomerId(customerId, pageable))
                .totalElement(orderRepository.countByCustomerId(customerId))
                .pageable(pageable)
                .build();
    }

    /**
     * @param orderId
     * # 주문 단건 조회
     */
    public OrderModel getOrderModel(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    /**
     * @param userId
     * @param date
     * # 누적 매출 계산
     */
    public CalculateSalesModel getCumulativeSales(String userId, LocalDate date) {
        Long cumulativeSales = calculateSalesRepository.findCumulativeSalesByUserId(userId, date);
        return CalculateSalesModel.builder()
                .totalSales(cumulativeSales)
                .build();
    }
}
