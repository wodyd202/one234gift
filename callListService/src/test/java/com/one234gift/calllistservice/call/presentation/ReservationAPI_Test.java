package com.one234gift.calllistservice.call.presentation;

import com.one234gift.calllistservice.call.application.ReservationCallRepository;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.one234gift.calllistservice.call.domain.CallFixture.aReservationCall;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 예약콜 API 테스트
 */
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "000-0000-0000")
public class ReservationAPI_Test {
    @Autowired MockMvc mockMvc;
    @Autowired ReservationCallRepository reservationCallRepository;

    @Test
    void 미래일자의_예약콜_리스트_조회() throws Exception{
        // when
        mockMvc.perform(get("/api/reservation-call")
                        .param("page", "0")
                        .param("size", "10")
                        .param("future", "true"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 이미_읽은_예약콜_리스트_조회() throws Exception {
        // when
        mockMvc.perform(get("/api/reservation-call")
                        .param("page", "0")
                        .param("size", "10")
                        .param("read", "true"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 읽지않은_예약콜_리스트_조회() throws Exception {
        // when
        mockMvc.perform(get("/api/reservation-call")
                        .param("page", "0")
                        .param("size", "10")
                        .param("read", "false"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 예약콜_읽음_처리() throws Exception{
        // given
        reservationCallRepository.save(aReservationCall().reserver(new Reserver("000-0000-0000")).build());

        // when
        mockMvc.perform(get("/api/reservation-call/{salesHistoryId}", "salesHistoryId"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 다른_영업자의_예약콜을_읽음_처리하면_에러발생() throws Exception{
        // given
        reservationCallRepository.save(aReservationCall().salesHistoryId(new SalesHistoryId("otherSalesHistoryId")).reserver(new Reserver("other")).build());

        // when
        mockMvc.perform(get("/api/reservation-call/{salesHistoryId}", "otherSalesHistoryId"))

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 해당_예약콜이_존재하지_않으면_에러발생() throws Exception{
        // when
        mockMvc.perform(get("/api/reservation-call/{salesHistoryId}", "notfound"))

        // then
        .andExpect(status().isBadRequest());
    }
}
