package com.one234gift.calllistservice.call.domain;

import com.one234gift.calllistservice.call.application.CallMapper;
import com.one234gift.calllistservice.call.application.model.ReservatedCallEvent;
import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;
import com.one234gift.calllistservice.call.domain.read.TargetCustomerModel;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import com.one234gift.calllistservice.call.domain.value.TargetCustomer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.one234gift.calllistservice.call.domain.CallFixture.aReservationCall;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        ReservationCallModel callInfoModel = callInfo.toModel();
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
        ReservationCallModel callInfoModel = callInfo.toModel();
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
    void 자신의_예약콜_읽음(){
        // given
        ReservationCall reservationCall = aReservationCall().when(LocalDate.now()).reserver(new Reserver("reader")).build();

        // when
        reservationCall.read(new Reserver("reader"));
        ReservationCallModel callInfoModel = reservationCall.toModel();

        // then
        assertTrue(callInfoModel.isRead());
    }

    @Test
    void 미래일자의_예약콜_읽음처리시_상태를_변경하지_않음(){
        // given
        ReservationCall reservationCall = aReservationCall().when(LocalDate.now().plusDays(1)).reserver(new Reserver("reader")).build();

        // when
        reservationCall.read(new Reserver("reader"));
        ReservationCallModel callInfoModel = reservationCall.toModel();

        // then
        assertFalse(callInfoModel.isRead());
    }

    @Test
    void 다른사원의_예약콜을_읽음처리시_에러발생(){
        // given
        ReservationCall reservationCall = aReservationCall().reserver(new Reserver("reader")).build();

        // when
        assertThrows(IllegalArgumentException.class, ()->{
            reservationCall.read(new Reserver("ohter"));
        });
    }

    @Test
    void 예약콜_확인후_수정시_읽지않음_상태로_변경됨(){
        // given
        ReservationCall reservationCall = aReservationCall().reserver(new Reserver("reader")).build();
        reservationCall.read(new Reserver("reader"));

        // when
        reservationCall.changeWhen(LocalDate.now().plusDays(3));
        ReservationCallModel callInfoModel = reservationCall.toModel();

        // then
        assertFalse(callInfoModel.isRead());
    }
}
