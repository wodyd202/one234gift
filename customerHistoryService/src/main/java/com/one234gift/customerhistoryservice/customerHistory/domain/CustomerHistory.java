package com.one234gift.customerhistoryservice.domain;

import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import lombok.Builder;

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
    private long customerId;

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
    private String who;

    /**
     * 변경일
     */
    @Column(nullable = false)
    private LocalDateTime createDateTime;

    protected CustomerHistory(){}

    @Builder
    public CustomerHistory(long customerId, String type, String payload, String who) {
        this.customerId = customerId;
        this.type = type;
        this.payload = payload;
        this.who = who;
        createDateTime = LocalDateTime.now();
    }

    public CustomerHistoryModel toModel() {
        return CustomerHistoryModel.builder()
                .createDateTime(createDateTime)
                .customerId(customerId)
                .who(who)
                .payload(payload)
                .type(type)
                .build();
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public String getWho() {
        return who;
    }
}
