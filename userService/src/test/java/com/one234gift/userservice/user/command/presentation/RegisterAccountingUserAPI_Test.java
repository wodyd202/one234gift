package com.one234gift.userservice.user.command.presentation;

import com.one234gift.userservice.command.application.model.RegisterUser;
import com.one234gift.userservice.domain.value.UserRole;
import com.one234gift.userservice.user.UserAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.userservice.user.UserFixture.aRegisterUser;
import static com.one234gift.userservice.user.UserFixture.aUser;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 사원 등록 API 테스트
 */
public class RegisterAccountingUserAPI_Test extends UserAPITest {

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_USER")
    void 사원_생성() throws Exception{
        // given
        RegisterUser registerUser = aRegisterUser().role(UserRole.ACCOUNTING_USER).build();

        // when
        mockMvc.perform(post("/api/user")
                .content(objectMapper.writeValueAsString(registerUser))
                .contentType(APPLICATION_JSON))

        // then
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_USER")
    void 중복된_사원이_이미_존재함() throws Exception {
        // given
        persistUser(aUser("134-1234-1234"));
        RegisterUser registerUser = aRegisterUser().phone("134-1234-1234").build();

        // when
        mockMvc.perform(post("/api/user")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType(APPLICATION_JSON))

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "SALES_USER")
    void 사원_등록은_경리담당자만_할_수_있음() throws Exception {
        // given
        RegisterUser registerUser = aRegisterUser().phone("134-1234-1234").build();

        // when
        mockMvc.perform(post("/api/user")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType(APPLICATION_JSON))

        // then
        .andExpect(status().isForbidden());
    }

}
