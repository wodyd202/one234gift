package com.one234gift.calllistservice.call.domain;

import com.one234gift.calllistservice.call.application.model.ReservatedCallEvent;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import com.one234gift.calllistservice.call.domain.value.TargetCustomer;

import java.time.LocalDate;

public class CallFixture {

    public static ReservationCall.ReservationCallBuilder aReservationCall(){
        return ReservationCall.builder()
                .salesHistoryId(new SalesHistoryId("salesHistoryId"))
                .customer(TargetCustomer.builder()
                        .customerId(1)
                        .category("분류")
                        .name("고객 업체명")
                        .build())
                .reserver(new Reserver("예약자"))
                .when(LocalDate.now().plusDays(2));
    }

    public static ReservatedCallEvent.ReservatedCallEventBuilder aReservatedCallEvent() {
        return ReservatedCallEvent.builder()
                .salesHistoryId("salesHistoryId")
                .customer(ReservatedCallEvent.Customer.builder()
                        .customerId(1)
                        .category("분류")
                        .businessInfo(ReservatedCallEvent.Customer.BusinessInfo.builder()
                                .name("고객 업체명")
                                .build())
                        .build())
                .reserver("예약자")
                .callReservationDate(LocalDate.now().plusDays(2));
    }
}
