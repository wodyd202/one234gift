package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.model.ChangeCallReservationDate;
import com.one234gift.customerservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.customerservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.customerservice.domain.model.RegisterSalesHistory;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import com.one234gift.customerservice.domain.value.CustomerReactivity;
import com.one234gift.customerservice.domain.value.HistoryContent;
import com.one234gift.customerservice.domain.value.Manager;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "sales_history")
@DynamicUpdate
public class SalesHistory {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false, name = "customer_id")
    private Long customerId;

    @Embedded
    @AttributeOverride(name = "content", column = @Column(name = "content", nullable = false, length = 200))
    private HistoryContent content;

    @Column(nullable = false)
    private boolean sample;

    @Column(nullable = false)
    private boolean catalogue;

    private LocalDate callReservationDate;

    @Enumerated(STRING)
    private CustomerReactivity reactivity;

    @Column(nullable = false)
    private final LocalDateTime createDateTime;

    @Embedded
    private final Manager manager;

    protected SalesHistory(){
        createDateTime = null;
        manager = null;
    }

    private SalesHistory(Long customerId, RegisterSalesHistory registerSalesHistory, Manager manager) {
        this.customerId = customerId;
        setContent(registerSalesHistory.getContent());
        sample = registerSalesHistory.isSample();
        catalogue = registerSalesHistory.isCatalogue();
        setCallReservationDate(registerSalesHistory.getCallReservationDate());
        reactivity = registerSalesHistory.getReactivity();
        this.manager = manager;
        createDateTime = LocalDateTime.now();
    }

    private void setContent(String content) {
        if(content == null){
            throw new IllegalArgumentException("영업 내용을 입력해주세요.");
        }
        this.content = new HistoryContent(content);
    }

    private void setCallReservationDate(LocalDate callReservationDate) {
        LocalDate now = LocalDate.now();
        if(callReservationDate != null && (callReservationDate.isEqual(now) || callReservationDate.isBefore(now))) {
            throw new IllegalArgumentException("예약콜은 오늘 이후 날짜로 지정해주세요.");
        }
        this.callReservationDate = callReservationDate;
    }

    public static SalesHistory register(Long customerId, RegisterSalesHistory registerSalesHistory, Manager manager) {
        return new SalesHistory(customerId, registerSalesHistory, manager);
    }

    /**
     * 샘플 유무 변경
     */
    public void changeSample() {
        sample = !sample;
    }

    /**
     * 카탈로그 유무 변경
     */
    public void changeCatalogue() {
        catalogue = !catalogue;
    }

    /**
     * @param salesHistoryContent
     * - 내용_변경
     */
    public void changeContent(ChangeSalesHistoryContent salesHistoryContent) {
        setContent(salesHistoryContent.getContent());
    }

    /**
     * @param callReservationDate
     * - 예약콜 변경
     */
    public void changeCallReservationDate(ChangeCallReservationDate callReservationDate) {
        setCallReservationDate(callReservationDate.getCallReservationDate());
    }

    /**
     * @param customerReactivity
     * - 반응도 변경
     */
    public void changeReactivity(ChangeCustomerReactivity customerReactivity) {
        this.reactivity = customerReactivity.getReactivity();
    }

    public SalesHistoryModel toModel() {
        return SalesHistoryModel.builder()
                .id(id)
                .customerId(customerId)
                .content(content.get())
                .sample(sample)
                .catalogue(catalogue)
                .callReservationDate(callReservationDate)
                .reactivity(reactivity)
                .createDateTime(createDateTime)
                .manager(manager.toModel())
                .build();
    }
}
