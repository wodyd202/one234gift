package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.SalesHistoryAPITest;
import com.one234gift.saleshistoryservice.domain.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 영업기록 변경 API 테스트
 */
@WithMockUser(username = "000-0000-0000")
public class ChangeSalesHistoryAPI_Test extends SalesHistoryAPITest {

    @BeforeEach
    void setUp(){
        // 사용자 생성
        newUser("000-0000-0000");
    }

    @Test
    void 샘플_유무_변경() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/sample", salesHistoryModel.getId()))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 카탈로그_유무_변경() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());

        // when
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/catalogue", salesHistoryModel.getId()))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 내용_변경() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());
        ChangeSalesHistoryContent changeSalesHistoryContent = ChangeSalesHistoryContent.builder()
                .content("내용 수정")
                .build();

        // when
        assertChangeContent(salesHistoryModel, changeSalesHistoryContent,

        // then
        status().isOk());
    }

    @Test
    void 내용_미입력() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());
        ChangeSalesHistoryContent changeSalesHistoryContent = ChangeSalesHistoryContent.builder()
                .content(null)
                .build();

        // when
        assertChangeContent(salesHistoryModel, changeSalesHistoryContent,

        // then
        status().isBadRequest());
    }

    @Test
    void 내용_빈값() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());
        ChangeSalesHistoryContent changeSalesHistoryContent = ChangeSalesHistoryContent.builder()
                .content("")
                .build();

        // when
        assertChangeContent(salesHistoryModel, changeSalesHistoryContent,

        // then
        status().isBadRequest());
    }

    @Test
    void 예약콜_변경() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());
        ChangeCallReservationDate changeCallReservationDate = ChangeCallReservationDate.builder()
                .callReservationDate(LocalDate.now().plusDays(1))
                .build();

        // when
        assertChangeCallReservationDate(salesHistoryModel, changeCallReservationDate,

        // then
        status().isOk());
    }

    @Test
    void 예약콜은_미래일자만_허용() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());
        ChangeCallReservationDate changeCallReservationDate = ChangeCallReservationDate.builder()
                .callReservationDate(LocalDate.now())
                .build();

        // when
        assertChangeCallReservationDate(salesHistoryModel, changeCallReservationDate,

        // then
        status().isBadRequest());
    }

    @Test
    void 반응도_변경() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());
        ChangeCustomerReactivity changeCustomerReactivity = ChangeCustomerReactivity.builder()
                .reactivity(CustomerReactivity.FOUR)
                .build();

        // when
        assertChangeReactivity(salesHistoryModel, changeCustomerReactivity,

        // then
        status().isOk());
    }

    @Test
    void 반응도_빈값() throws Exception {
        // given
        SalesHistoryModel salesHistoryModel = newSalesHistory(aRegisterSalesHistory().build());
        ChangeCustomerReactivity changeCustomerReactivity = ChangeCustomerReactivity.builder()
                .reactivity(null)
                .build();

        // when
        assertChangeReactivity(salesHistoryModel, changeCustomerReactivity,

        // then
        status().isBadRequest());
    }

    private void assertChangeContent(SalesHistoryModel salesHistoryModel, ChangeSalesHistoryContent changeSalesHistoryContent, ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/content", salesHistoryModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeSalesHistoryContent)))
                .andExpect(resultMatcher);
    }

    private void assertChangeCallReservationDate(SalesHistoryModel salesHistoryModel, ChangeCallReservationDate changeCallReservationDate, ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/call-reservation-date", salesHistoryModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeCallReservationDate)))
                .andExpect(resultMatcher);
    }

    private void assertChangeReactivity(SalesHistoryModel salesHistoryModel, ChangeCustomerReactivity changeCustomerReactivity, ResultMatcher resultMatcher) throws Exception{
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/reactivity", salesHistoryModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeCustomerReactivity)))
                .andExpect(resultMatcher);
    }
}
