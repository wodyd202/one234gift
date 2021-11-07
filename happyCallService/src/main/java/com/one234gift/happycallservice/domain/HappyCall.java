package com.one234gift.happycallservice.domain;

import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

/**
 * 예약콜
 */
@Entity
@Table(name = "happy_call", indexes = {
        @Index(columnList = "phone, when")
})
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
abstract public class HappyCall {
    /**
     * 예약콜 고유 번호
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long seq;

    /**
     * 예약콜 일자
     */
    @Column(nullable = false)
    protected final LocalDate when;

    /**
     * 고객
     */
    @Embedded
    protected final CustomerInfo targetCustomer;

    /**
     * 전화 예약자
     */
    @Embedded
    protected final SalesUserInfo salesUser;

    /**
     * 읽음
     */
    @Column(nullable = false)
    protected boolean read;

    protected HappyCall(){
        when = null;
        targetCustomer = null;
        salesUser = null;
    }

    protected HappyCall(LocalDate when, CustomerInfo customerInfo, SalesUserInfo salesUser) {
        this.when = when;
        this.targetCustomer = customerInfo;
        this.salesUser = salesUser;
        read = false;
    }

    public void read() {
        this.read = true;
    }

    public abstract HappyCallModel toModel();
}
