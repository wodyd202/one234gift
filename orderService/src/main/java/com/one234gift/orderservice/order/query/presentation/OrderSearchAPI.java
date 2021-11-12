package com.one234gift.orderservice.order.query.presentation;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import com.one234gift.orderservice.order.query.application.OrderSearchService;
import com.one234gift.orderservice.order.query.application.model.CalculateSalesModel;
import com.one234gift.orderservice.order.query.application.model.OrderModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("api/order")
public class OrderSearchAPI {
    @Autowired
    private OrderSearchService orderSearchService;

    /**
     * @param pageable
     * @param principal
     * # 해당 영업 사원의 주문 리스트 조회
     */
    @GetMapping
    public ResponseEntity<OrderModels> getOrderModels(Pageable pageable, Principal principal){
        OrderModels orderModels = orderSearchService.getOrderModelsByUserId(principal.getName(), pageable);
        return ResponseEntity.ok(orderModels);
    }

    /**
     * @param customerId
     * # 해당 고객의 주문 리스트 조회
     */
    @GetMapping("customer/{customerId}")
    public ResponseEntity<OrderModels> getOrderModelsByCustomerId(@PathVariable long customerId, Pageable pageable){
        OrderModels orderModels = orderSearchService.getOrderModelsByCustomerId(customerId, pageable);
        return ResponseEntity.ok(orderModels);
    }

    /**
     * @param orderId
     * # 주문 단건 조회
     */
    @GetMapping("{orderId}")
    public ResponseEntity<OrderModel> findById(@PathVariable long orderId){
        OrderModel orderModel = orderSearchService.getOrderModel(orderId);
        return ResponseEntity.ok(orderModel);
    }

    /**
     * @param userId
     * # 누적 매출 계산
     */
    @GetMapping("cumulative-sales/{userId}")
    public ResponseEntity<CalculateSalesModel> findCumulativeSales(@PathVariable String userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        CalculateSalesModel cumulativeSales = orderSearchService.getCumulativeSales(userId, date);
        return ResponseEntity.ok(cumulativeSales);
    }
}
