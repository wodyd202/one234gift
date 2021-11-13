package com.one234gift.calllistservice.call.command.domain;

import com.one234gift.calllistservice.call.command.application.CallMapper;
import com.one234gift.calllistservice.call.command.application.model.ReservatedCallEvent;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.read.CallInfoModel;
import com.one234gift.calllistservice.call.domain.read.TargetCustomerModel;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import com.one234gift.calllistservice.call.domain.value.TargetCustomer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 예약콜 테스트
 */
public class CallInfo_Test {

    @Test
    void 예약콜_생성(){
        // when
        ReservationCall callInfo = ReservationCall.builder()
                .salesHistoryId(new SalesHistoryId("salesHistoryId"))
                .customer(TargetCustomer.builder()
                        .customerId(1)
                        .category("분류")
                        .name("고객 업체명")
                        .build())
                .reserver(new Reserver("예약자"))
                .when(LocalDate.now().plusDays(2))
                .build();
        CallInfoModel callInfoModel = callInfo.toModel();
        TargetCustomerModel customerModel = callInfoModel.getCustomer();

        // then
        assertEquals(callInfoModel.getWhen(), LocalDate.now().plusDays(2));
        assertEquals(callInfoModel.getSalesHistoryId(), "salesHistoryId");
        assertEquals(customerModel.getCustomerId(), 1);
        assertEquals(customerModel.getCategory(), "분류");
        assertEquals(customerModel.getName(), "고객 업체명");
        assertEquals(callInfoModel.getReserver(), "예약자");
        assertEquals(callInfoModel.getWhen(), LocalDate.now().plusDays(2));
    }

    @Test
    void mapFrom(){
        // given
        ReservatedCallEvent reservatedCallEvent = ReservatedCallEvent.builder()
                .salesHistoryId("salesHistoryId")
                .customer(ReservatedCallEvent.Customer.builder()
                        .customerId(1)
                        .category("분류")
                        .businessInfo(ReservatedCallEvent.Customer.BusinessInfo.builder()
                                .name("고객 업체명")
                                .build())
                        .build())
                .reserver("예약자")
                .callReservationDate(LocalDate.now().plusDays(2))
                .build();

        // when
        CallMapper callInfoMapper = new CallMapper();
        ReservationCall callInfo = callInfoMapper.mapFrom(reservatedCallEvent);
        CallInfoModel callInfoModel = callInfo.toModel();
        TargetCustomerModel customerModel = callInfoModel.getCustomer();

        // then
        assertEquals(callInfoModel.getWhen(), LocalDate.now().plusDays(2));
        assertEquals(callInfoModel.getSalesHistoryId(), "salesHistoryId");
        assertEquals(customerModel.getCustomerId(), 1);
        assertEquals(customerModel.getCategory(), "분류");
        assertEquals(customerModel.getName(), "고객 업체명");
        assertEquals(callInfoModel.getReserver(), "예약자");
        assertEquals(callInfoModel.getWhen(), LocalDate.now().plusDays(2));
    }
}
