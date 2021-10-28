package com.one234gift.saleshistoryservice.command.application.event;

import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.read.WriterModel;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CallReserved {
    private final WriterModel writer;
    private final long customerId;
    private final LocalDate callReservationDate;

    public CallReserved(SalesHistoryModel salesHistoryModel) {
        this.writer = salesHistoryModel.getManager();
        this.customerId = salesHistoryModel.getCustomerId();
        this.callReservationDate = salesHistoryModel.getCallReservationDate();
    }
}
