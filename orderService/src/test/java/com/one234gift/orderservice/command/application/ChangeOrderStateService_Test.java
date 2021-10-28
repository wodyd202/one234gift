package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.OrderAPITest;
import com.one234gift.orderservice.command.application.exception.OrderNotFoundException;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.orderservice.OrderFixture.aOrder;
import static com.one234gift.orderservice.OrderFixture.aSalesUser;
import static com.one234gift.orderservice.domain.value.OrderState.CENCEL;
import static com.one234gift.orderservice.domain.value.OrderState.FINISH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * 주문 상태 변경 테스트
 */
public class ChangeOrderStateService_Test extends OrderAPITest {
    @Autowired ChangeOrderStateService changeOrderStateService;

    @Test
    void 자신의_주문만_상태를_변경할_수있음(){
        assertThrows(OrderNotFoundException.class, ()->{
            // given
            OrderModel orderModel = newOrder(aOrder(aSalesUser("000-0000-0000")));

            // when
            changeOrderStateService.cencel(orderModel.getId(), "other");
        });
    }

    @Test
    void 주문_취소(){
        // given
        OrderModel orderModel = newOrder(aOrder(aSalesUser("000-0000-0000")));

        // when
        orderModel = changeOrderStateService.cencel(orderModel.getId(), "000-0000-0000");

        // then
        assertEquals(orderModel.getState(), CENCEL);
    }

    @Test
    void 주문_완료_처리(){
        // given
        OrderModel orderModel = newOrder(aOrder(aSalesUser("000-0000-0000")));

        // when
        orderModel = changeOrderStateService.finish(orderModel.getId(), "000-0000-0000");

        // then
        assertEquals(orderModel.getState(), FINISH);
    }
}
