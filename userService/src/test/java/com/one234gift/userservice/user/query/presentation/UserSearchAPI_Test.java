package com.one234gift.userservice.user.query.presentation;

import com.one234gift.userservice.user.UserAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.userservice.user.UserFixture.aUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 사원 조회 API 테스트
 */
public class UserSearchAPI_Test extends UserAPITest {

    @Test
    @WithMockUser(username = "123-1234-1237", roles = "SALES_USER")
    void 다른_사원의_정보를_조회할_수_없음() throws Exception{
        // given
        persistUser(aUser("010-0000-0000"));

        // when
        mockMvc.perform(get("/api/user/{phone}","010-0000-0000"))

        // then
        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "123-1234-1236", roles = "SALES_USER")
    void 자신의_정보_조회() throws Exception {
        // given
        persistUser(aUser("123-1234-1236"));

        // when
        mockMvc.perform(get("/api/user/{phone}","123-1234-1236"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "123-1234-1235", roles = "ACCOUNTING_USER")
    void 경리담당자는_다른_사원의_정보를_조회할_수_있음() throws Exception {
        // given
        persistUser(aUser("010-0000-0000"));

        // when
        mockMvc.perform(get("/api/user/{phone}","010-0000-0000"))

        // then
        .andExpect(status().isOk());
    }
}
