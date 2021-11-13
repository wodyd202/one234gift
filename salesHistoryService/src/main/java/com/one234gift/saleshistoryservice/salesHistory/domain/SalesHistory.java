package com.one234gift.saleshistoryservice.salesHistory.domain;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Customer;
import com.one234gift.saleshistoryservice.salesHistory.domain.event.*;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.*;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;

/**
 * 영업 기록
 */
@Entity
@Table(name = "sales_history")
@DynamicUpdate
public class SalesHistory extends AbstractAggregateRoot<SalesHistory> {

    /**
     * 영업 기록 고유 값
     */
    @EmbeddedId
    private SalesHistoryId id;

    /**
     * 고객 고유 번호
     */
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "customer_id", nullable = false))
    private CustomerId customerId;

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
    private LocalDate createDate;

    /**
     * 작성자
     */
    @Embedded
    private Writer writer;

    protected SalesHistory(){}

    @Builder
    public SalesHistory(SalesHistoryId id,
                        Customer customer,
                        HistoryContent content,
                        boolean sample,
                        boolean catalogue,
                        LocalDate callReservationDate,
                        CustomerReactivity reactivity,
                        Writer writer) {
        setId(id);
        setCustomer(customer);
        this.sample = sample;
        this.catalogue = catalogue;
        setContent(content);
        setReactivity(reactivity);
        setWriter(writer);
        setCallReservationDate(callReservationDate, customer);
        this.createDate = LocalDate.now();

        // 영업기록 생성 이벤트
        registerEvent(RegisteredSalesHistoryEvent.builder()
                .salesHistoryId(id.get())
                .customerId(customerId.get())
                .historyContent(content.get())
                .callReservationDate(callReservationDate)
                .catalogue(catalogue)
                .createDate(createDate)
                .reactivity(reactivity)
                .sample(sample)
                .build());
    }

    private void setId(SalesHistoryId id) {
        if(id == null){
            throw new IllegalArgumentException("영업 기록 고유 값을 입력해주세요.");
        }
        this.id = id;
    }

    private void setCustomer(Customer customer) {
        if(customer == null){
            throw new IllegalArgumentException("고객 고유 번호를 입력해주세요.");
        }
        this.customerId = new CustomerId(customer.getCustomerId());
    }

    private void setWriter(Writer writer){
        if(writer == null){
            throw new IllegalArgumentException("작성자를 입력해주세요.");
        }
        this.writer = writer;
    }

    private void setReactivity(CustomerReactivity reactivity) {
        if(reactivity == null){
            throw new IllegalArgumentException("반응도를 입력해주세요.");
        }
        this.reactivity = reactivity;
    }

    private void setContent(HistoryContent content) {
        if(content == null){
            throw new IllegalArgumentException("영업 내용을 입력해주세요.");
        }
        this.content = content;
    }

    private void setCallReservationDate(LocalDate callReservationDate, Customer customer) {
        LocalDate now = LocalDate.now();
        if(callReservationDate != null && (callReservationDate.isEqual(now) || callReservationDate.isBefore(now))) {
            throw new IllegalArgumentException("예약콜은 오늘 이후 날짜로 지정해주세요.");
        }
        // 예약콜을 등록하는 경우
        if(this.callReservationDate == null && callReservationDate != null){
            registerEvent(CallReservedEvent.builder()
                    .reserver(writer.getPhone())
                    .salesHistoryId(id.get())
                    .callReservationDate(callReservationDate)
                    .customer(customer)
                    .build());
        }
        // 예약콜을 변경하는 경우
        if(this.callReservationDate != null && callReservationDate != null){
            registerEvent(new ChangedCallReservation(id.get(), callReservationDate));
        }
        // 예약콜을 제거하는 경우
        if(this.callReservationDate != null && callReservationDate == null){
            registerEvent(new RemovedCallReservationEvent(id.get()));
        }
        this.callReservationDate = callReservationDate;
    }

    /**
     * 샘플 유무 변경
     * @param updater
     */
    public void changeSample(Writer updater) {
        verifyIsUpdaterSaleshistory(updater);
        sample = !sample;

        // 샘플 유무 변경 이벤트
        registerEvent(new ChangedSampleEvent(id.get(), sample));
    }

    /**
     * 카탈로그 유무 변경
     * @param updater
     */
    public void changeCatalogue(Writer updater) {
        verifyIsUpdaterSaleshistory(updater);
        catalogue = !catalogue;

        // 카탈로그 유무 변경 이벤트
        registerEvent(new ChangedCatalogueEvent(id.get(), catalogue));
    }

    /**
     * @param changeContent
     * - 내용_변경
     * @param updater
     */
    public void changeContent(HistoryContent changeContent, Writer updater) {
        verifyIsUpdaterSaleshistory(updater);
        setContent(changeContent);

        // 내용 변경 이벤트
        registerEvent(new ChangedContentEvent(id.get(), content.get()));
    }

    /**
     * @param changeCallReservationDate
     * - 예약콜 변경
     * @param updater
     */
    public void changeCallReservationDate(LocalDate changeCallReservationDate, Customer customer, Writer updater) {
        verifyIsUpdaterSaleshistory(updater);
        setCallReservationDate(changeCallReservationDate, customer);
    }

    /**
     * @param customerReactivity
     * - 반응도 변경
     */
    public void changeReactivity(CustomerReactivity customerReactivity, Writer updater) {
        verifyIsUpdaterSaleshistory(updater);
        this.reactivity = customerReactivity;

        // 반응도 변경 이벤트
        registerEvent(new ChangedReactivityEvent(id.get(), reactivity));
    }

    private void verifyIsUpdaterSaleshistory(Writer updater){
        if(!this.writer.equals(updater)){
            throw new IllegalArgumentException("다른 영업 담당자의 영업 기록을 수정할 수 없습니다.");
        }
    }

    /**
     * @param deleter
     * # 영업 기록 삭제 가능 여부 체크
     */
    public boolean removeAble(Writer deleter) {
        return this.writer.equals(deleter);
    }

    public SalesHistoryModel toModel() {
        return SalesHistoryModel.builder()
                .id(id.get())
                .customerId(customerId)
                .content(content)
                .sample(sample)
                .catalogue(catalogue)
                .callReservationDate(callReservationDate)
                .reactivity(reactivity)
                .createDate(createDate)
                .writer(writer)
                .build();
    }
}
