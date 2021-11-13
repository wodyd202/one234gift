package com.one234gift.happycallservice.domain;

import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.Reserver;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 예약콜
 */
@Entity
@Table(name = "happy_call", indexes = {
        @Index(columnList = "phone, when")
})
@DynamicUpdate
public class CallReservation {

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
    protected LocalDate when;

    /**
     * 고객
     */
    @Embedded
    protected CustomerInfo targetCustomer;

    /**
     * 전화 예약자
     */
    @Embedded
    protected Reserver reserver;

    /**
     * 읽음
     */
    @Column(nullable = false)
    protected boolean read;

    protected CallReservation(){}

    @Builder
    public CallReservation(LocalDate when, CustomerInfo targetCustomer, Reserver reserver) {
        this.when = when;
        this.targetCustomer = targetCustomer;
        this.reserver = reserver;
        this.read = true;
    }
}
