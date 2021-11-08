package com.one234gift.saleshistoryservice.domain.read;

import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;
import com.one234gift.saleshistoryservice.domain.value.HistoryContent;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class SalesHistoryModel {
    private Long id;
    private Long customerId;
    private String content;
    private boolean sample, catalogue;
    private LocalDate callReservationDate;
    private CustomerReactivity reactivity;
    private LocalDateTime createDateTime;
    private WriterModel writer;


    /**
     * 영업 기록 단건 조회시 사용
     */
    @Builder
    public SalesHistoryModel(Long id,
                             Long customerId,
                             HistoryContent content,
                             boolean sample,
                             boolean catalogue,
                             LocalDate callReservationDate,
                             CustomerReactivity reactivity,
                             LocalDateTime createDateTime,
                             Writer writer){
        this.id = id;
        this.customerId = customerId;
        this.content = content.get();
        this.sample = sample;
        this.catalogue = catalogue;
        this.callReservationDate = callReservationDate;
        this.reactivity = reactivity;
        this.createDateTime = createDateTime;
        this.writer = writer.toModel();
    }

    /**
     * 영업 기록 리스트 조회시 사용
     */
    @Builder
    public SalesHistoryModel(Long id,
                             HistoryContent content,
                             LocalDateTime createDateTime,
                             Writer writer) {
        this.id = id;
        this.content = content.get();
        this.createDateTime = createDateTime;
        this.writer = writer.toModel();
    }

    public boolean existCallReservation() {
        return this.callReservationDate != null;
    }
}
