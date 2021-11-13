package com.one234gift.saleshistoryservice.salesHistory.domain.read;

import com.one234gift.saleshistoryservice.salesHistory.domain.value.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SalesHistoryModel {
    private String id;
    private Long customerId;
    private String content;
    private boolean sample, catalogue;
    private LocalDate callReservationDate;
    private CustomerReactivity reactivity;
    private LocalDate createDate;
    private WriterModel writer;

    /**
     * 영업 기록 단건 조회시 사용
     */
    @Builder
    public SalesHistoryModel(String id,
                             CustomerId customerId,
                             HistoryContent content,
                             boolean sample,
                             boolean catalogue,
                             LocalDate callReservationDate,
                             CustomerReactivity reactivity,
                             LocalDate createDate,
                             Writer writer){
        this.id = id;
        this.customerId = customerId.get();
        this.content = content.get();
        this.sample = sample;
        this.catalogue = catalogue;
        this.callReservationDate = callReservationDate;
        this.reactivity = reactivity;
        this.createDate = createDate;
        this.writer = writer.toModel();
    }

    /**
     * 해당 고객에 대한 영업 기록 리스트 조회시 사용
     */
    @Builder
    public SalesHistoryModel(SalesHistoryId id,
                             HistoryContent content,
                             LocalDate createDate,
                             Writer writer) {
        this.id = id.get();
        this.content = content.get();
        this.createDate = createDate;
        this.writer = writer.toModel();
    }

    public boolean existCallReservation() {
        return this.callReservationDate != null;
    }
}
