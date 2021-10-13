package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.APITest;
import com.one234gift.saleshistoryservice.domain.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000")
public class ChangeSalesHistoryAPI_Test extends APITest {

    SalesHistoryModel salesHistory;

    @Override
    public void init() {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().build();
        this.salesHistory = registerSalesHistoryService.register(salesHistory);
    }

    @Test
    void 샘플_유무_변경() throws Exception {
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/sample", salesHistory.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void 카탈로그_유무_변경() throws Exception {
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/catalogue", salesHistory.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void 내용_변경() throws Exception {
        ChangeSalesHistoryContent changeSalesHistoryContent = ChangeSalesHistoryContent.builder()
                .content("내용 수정")
                .build();
        assertChangeContent(changeSalesHistoryContent, status().isOk());
    }

    @Test
    void 내용_미입력() throws Exception {
        ChangeSalesHistoryContent changeSalesHistoryContent = ChangeSalesHistoryContent.builder()
                .content(null)
                .build();
        assertChangeContent(changeSalesHistoryContent, status().isBadRequest());
    }

    @Test
    void 내용_빈값() throws Exception {
        ChangeSalesHistoryContent changeSalesHistoryContent = ChangeSalesHistoryContent.builder()
                .content("")
                .build();
        assertChangeContent(changeSalesHistoryContent, status().isBadRequest());
    }

    @Test
    void 예약콜_변경() throws Exception {
        ChangeCallReservationDate changeCallReservationDate = ChangeCallReservationDate.builder()
                .callReservationDate(LocalDate.now().plusDays(1))
                .build();
        assertChangeCallReservationDate(changeCallReservationDate, status().isOk());
    }

    @Test
    void 예약콜은_미래일자만_허용() throws Exception {
        ChangeCallReservationDate changeCallReservationDate = ChangeCallReservationDate.builder()
                .callReservationDate(LocalDate.now())
                .build();
        assertChangeCallReservationDate(changeCallReservationDate, status().isBadRequest());
    }

    @Test
    void 반응도_변경() throws Exception {
        ChangeCustomerReactivity changeCustomerReactivity = ChangeCustomerReactivity.builder()
                .reactivity(CustomerReactivity.FOUR)
                .build();
        assertChangeReactivity(changeCustomerReactivity, status().isOk());
    }

    @Test
    void 반응도_빈값() throws Exception {
        ChangeCustomerReactivity changeCustomerReactivity = ChangeCustomerReactivity.builder()
                .reactivity(null)
                .build();
        assertChangeReactivity(changeCustomerReactivity, status().isBadRequest());
    }

    private void assertChangeContent(ChangeSalesHistoryContent changeSalesHistoryContent, ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/content", salesHistory.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeSalesHistoryContent)))
                .andExpect(resultMatcher);
    }

    private void assertChangeCallReservationDate(ChangeCallReservationDate changeCallReservationDate, ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/call-reservation-date", salesHistory.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeCallReservationDate)))
                .andExpect(resultMatcher);
    }

    private void assertChangeReactivity(ChangeCustomerReactivity changeCustomerReactivity, ResultMatcher resultMatcher) throws Exception{
        mockMvc.perform(put("/api/sales-history/{salesHistoryId}/reactivity", salesHistory.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeCustomerReactivity)))
                .andExpect(resultMatcher);
    }
}
