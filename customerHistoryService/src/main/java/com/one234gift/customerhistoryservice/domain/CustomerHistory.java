package com.one234gift.customerhistoryservice.domain;

import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "customer_history")
public class CustomerHistory {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private String manager;

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
}
