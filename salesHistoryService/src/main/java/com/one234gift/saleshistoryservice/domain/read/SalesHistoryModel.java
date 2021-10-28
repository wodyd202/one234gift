package com.one234gift.saleshistoryservice.domain.read;

import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;
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
    private WriterModel manager;

    @Builder
    public SalesHistoryModel(Long id,
                             Long customerId,
                             String content,
                             boolean sample,
                             boolean catalogue,
                             LocalDate callReservationDate,
                             CustomerReactivity reactivity,
                             LocalDateTime createDateTime,
                             Writer manager) {
        this.id = id;
        this.customerId = customerId;
        this.content = content;
        this.sample = sample;
        this.catalogue = catalogue;
        this.callReservationDate = callReservationDate;
        this.reactivity = reactivity;
        this.createDateTime = createDateTime;
        this.manager = manager.toModel();
    }

    public boolean existCallReservation() {
        return this.callReservationDate != null;
    }
}
