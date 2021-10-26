package com.one234gift.customerhistoryservice.domain;

import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 고객 변경 이력
 */
@Entity
@Table(name = "customer_history")
public class CustomerHistory {
    /**
     * 변경이력 고유 번호
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /**
     * 고객 고유 번호
     */
    @Column(nullable = false)
    private String customerId;

    /**
     * 변경 타입(고객명 변경, 주소 변경 등)
     */
    @Column(nullable = false)
    private String type;

    /**
     * 변경 내용
     */
    @Column(nullable = false)
    private String payload;

    /**
     * 변경자
     */
    @Column(nullable = false)
    private String manager;

    /**
     * 변경일
     */
    @Column(nullable = false)
    private LocalDateTime createDateTime;

    protected CustomerHistory(){}

    private CustomerHistory(CustomerHistoryEvent customerHistoryEvent) {
        customerId = customerHistoryEvent.getCustomerId();
        type = customerHistoryEvent.getType();
        payload = customerHistoryEvent.getPayload();
        manager = customerHistoryEvent.getManager();
        createDateTime = LocalDateTime.now();
    }

    public static CustomerHistory register(CustomerHistoryEvent customerHistoryEvent) {
        return new CustomerHistory(customerHistoryEvent);
    }

    public CustomerHistoryModel toModel() {
        return CustomerHistoryModel.builder()
                .createDateTime(createDateTime)
                .customerId(customerId)
                .manager(manager)
                .payload(payload)
                .type(type)
                .build();
    }

    public String getCustomerId() {
        return customerId;
    }
}
