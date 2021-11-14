package com.one234gift.calllistservice.call.domain;

import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import com.one234gift.calllistservice.call.domain.value.TargetCustomer;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 예약콜 정보
 */
@Entity
@Table(name = "reservation_call")
@DynamicUpdate
public class ReservationCall {

    /**
     * 영업 기록
     */
    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "sales_history_id", nullable = false, length = 50))
    private SalesHistoryId salesHistoryId;

    /**
     * 고객 정보
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "customerId", column = @Column(name = "customer_id", nullable = false)),
            @AttributeOverride(name = "category", column = @Column(name = "customer_category", nullable = false, length = 15)),
            @AttributeOverride(name = "name", column = @Column(name = "customer_name", nullable = false, length = 30))
    })
    private TargetCustomer customer;

    /**
     * 예약자
     */
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "reserver", nullable = false, length = 15))
    private Reserver reserver;

    @Column(nullable = false)
    private LocalDate when;

    private boolean read;

    protected ReservationCall(){}

    @Builder
    public ReservationCall(SalesHistoryId salesHistoryId,
                           TargetCustomer customer,
                           Reserver reserver,
                           LocalDate when) {
        this.salesHistoryId = salesHistoryId;
        this.customer = customer;
        this.reserver = reserver;
        this.when = when;
        this.read = false;
    }

    /**
     * @param callReservationDate
     * # 예약콜 일자 변경
     */
    public void changeWhen(LocalDate callReservationDate) {
        this.when = callReservationDate;
        read = false;
    }

    /**
     * @param reader
     * # 예약콜 읽음 처리
     */
    public void read(Reserver reader) {
        if(!reserver.equals(reader)){
            throw new IllegalArgumentException("다른 영업사원의 예약콜을 읽음처리 할 수 없습니다.");
        }
        // 미래일자 예약콜은 읽음 상태를 변경하지 않음
        if(!when.isAfter(LocalDate.now())){
            read = true;
        }
    }

    public ReservationCallModel toModel(){
        return ReservationCallModel.builder()
                .salesHistoryId(salesHistoryId)
                .customer(customer)
                .reserver(reserver)
                .when(when)
                .read(read)
                .build();
    }
}
