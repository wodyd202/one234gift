package com.one234gift.saleshistoryservice.domain;

import com.one234gift.saleshistoryservice.domain.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;
import com.one234gift.saleshistoryservice.domain.value.HistoryContent;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * 영업 기록
 */
@Entity
@Table(name = "sales_history")
@DynamicUpdate
public class SalesHistory {

    /**
     * 영업 기록 고유 번호
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /**
     * 고객 고유 번호
     */
    @Column(nullable = false, name = "customer_id")
    private Long customerId;

    /**
     * 영업 내용
     */
    @Embedded
    @AttributeOverride(name = "content", column = @Column(name = "content", nullable = false, length = 200))
    private HistoryContent content;

    /**
     * 샘플 지급 유무
     */
    @Column(nullable = false)
    private boolean sample;

    /**
     * 카탈로그 지급 유무
     */
    @Column(nullable = false)
    private boolean catalogue;

    /**
     * 예약콜
     */
    private LocalDate callReservationDate;

    /**
     * 반응도
     */
    @Enumerated(STRING)
    private CustomerReactivity reactivity;

    /**
     * 기록 생성일
     */
    @Column(nullable = false)
    private final LocalDateTime createDateTime;

    /**
     * 작성자
     */
    @Embedded
    private final Writer writer;

    protected SalesHistory(){
        createDateTime = null;
        writer = null;
    }

    private SalesHistory(RegisterSalesHistory registerSalesHistory, Writer writer) {
        this.customerId = registerSalesHistory.getCustomerId();
        setContent(registerSalesHistory.getContent());
        sample = registerSalesHistory.isSample();
        catalogue = registerSalesHistory.isCatalogue();
        setCallReservationDate(registerSalesHistory.getCallReservationDate());
        reactivity = registerSalesHistory.getReactivity();
        this.writer = writer;
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

    public static SalesHistory register(RegisterSalesHistory registerSalesHistory, Writer manager) {
        return new SalesHistory(registerSalesHistory, manager);
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
                .content(content)
                .sample(sample)
                .catalogue(catalogue)
                .callReservationDate(callReservationDate)
                .reactivity(reactivity)
                .createDateTime(createDateTime)
                .writer(writer)
                .build();
    }
}
