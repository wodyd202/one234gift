package com.one234gift.happycallservice.domain;

import com.one234gift.happycallservice.domain.model.OrderEvent;
import com.one234gift.happycallservice.domain.read.HappyCallModel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "happy_call")
public class HappyCall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false, length = 13)
    private String userId;

    @Column(nullable = false)
    private long orderId;

    @Column(nullable = false)
    private LocalDate callDate;

    @Column(nullable = false)
    private boolean read;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerCategory;

    protected HappyCall(){}

    private HappyCall(OrderEvent orderEvent) {
        this.userId = orderEvent.getUserId();
        this.orderId = orderEvent.getId();
        this.customerName = orderEvent.getCustomerName();
        this.customerCategory = orderEvent.getCustomerCategory();
        this.callDate = LocalDate.now().plusDays(3);
        this.read = false;
    }

    public static HappyCall register(OrderEvent orderEvent) {
        return new HappyCall(orderEvent);
    }

    public void read() {
        this.read = true;
    }

    public HappyCallModel toModel() {
        return HappyCallModel.builder()
                .callDate(callDate)
                .seq(seq)
                .orderId(orderId)
                .read(read)
                .userId(userId)
                .customerCategory(customerCategory)
                .customerName(customerName)
                .build();
    }
}
