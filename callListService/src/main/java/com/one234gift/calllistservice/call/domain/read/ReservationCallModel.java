package com.one234gift.calllistservice.call.domain.read;

import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import com.one234gift.calllistservice.call.domain.value.TargetCustomer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationCallModel {
    private String salesHistoryId;
    private TargetCustomerModel customer;
    private String reserver;
    private LocalDate when;
    private boolean read;

    @Builder
    public ReservationCallModel(SalesHistoryId salesHistoryId,
                                TargetCustomer customer,
                                Reserver reserver,
                                LocalDate when,
                                boolean read) {
        this.salesHistoryId = salesHistoryId.get();
        this.customer = customer.toModel();
        this.reserver = reserver.get();
        this.when = when;
        this.read = read;
    }

    @Override
    public String toString() {
        return "CallInfoModel{" +
                "salesHistoryId='" + salesHistoryId + '\'' +
                ", customer=" + customer +
                ", reserver='" + reserver + '\'' +
                ", when=" + when +
                ", read=" + read +
                '}';
    }
}
