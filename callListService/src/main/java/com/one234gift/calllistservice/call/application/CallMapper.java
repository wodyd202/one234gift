package com.one234gift.calllistservice.call.application;

import com.one234gift.calllistservice.call.application.model.ReservatedCallEvent;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import com.one234gift.calllistservice.call.domain.value.TargetCustomer;
import org.springframework.stereotype.Component;

@Component
public class CallMapper {
    public ReservationCall mapFrom(ReservatedCallEvent reservatedCallEvent) {
        ReservatedCallEvent.Customer customer = reservatedCallEvent.getCustomer();
        return ReservationCall.builder()
                .salesHistoryId(new SalesHistoryId(reservatedCallEvent.getSalesHistoryId()))
                .customer(TargetCustomer.builder()
                        .customerId(customer.getCustomerId())
                        .category(customer.getCategory())
                        .name(customer.getBusinessInfo().getName())
                        .build())
                .reserver(new Reserver(reservatedCallEvent.getReserver()))
                .when(reservatedCallEvent.getCallReservationDate())
                .build();
    }
}
