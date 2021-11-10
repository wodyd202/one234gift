package com.one234gift.customerservice.customer.domain.value;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * 고객 담당 관련 도메인
 */
@Entity
@Table(name = "customer_reponsible")
public class Responsible {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long seq;

    private final Long customerId;
    private final String manager;

    private Responsible(){
        customerId = null;
        manager = null;
    }

    private Responsible(Long customerId, String manager) {
        this.customerId = customerId;
        this.manager = manager;
    }

    public static Responsible of(Long customerId, String manager) {
        return new Responsible(customerId, manager);
    }
}
