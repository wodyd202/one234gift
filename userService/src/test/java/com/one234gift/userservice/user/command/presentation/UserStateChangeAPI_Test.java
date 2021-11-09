package com.one234gift.userservice.user.command.presentation;

import com.one234gift.userservice.user.UserAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.userservice.user.UserFixture.aUser;
import static com.one234gift.userservice.user.UserFixture.leavedUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserStateChangeAPI_Test extends UserAPITest {

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_USER")
    void 재영입() throws Exception {
        // given
        persistUser(leavedUser("000-1234-0000"));

        // when
        mockMvc.perform(put("/api/user/{userPk}/comeback","000-1234-0000"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_USER")
    void 이미_재직중인_사원을_재영입_할_수_없음() throws Exception {
        // given
        persistUser(aUser("123-1234-1235"));

        // when
        mockMvc.perform(put("/api/user/{userPk}/comeback","123-1234-1235"))

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "SALES_USER")
    void 재영입_처리는_경리담당자만_접근_가능() throws Exception{
        // when
        mockMvc.perform(put("/api/user/{userPk}/comeback","fobidden"))

        // then
        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_USER")
    void 퇴사_처리() throws Exception {
        // given
        persistUser(aUser("123-1234-1234"));

        // when
        mockMvc.perform(delete("/api/user/{userPk}/leave", "123-1234-1234"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_USER")
    void 이미_퇴사한_사원을_퇴사할_수_없음() throws Exception {
        // given
        persistUser(leavedUser("123-1234-1210"));

        // when
        mockMvc.perform(delete("/api/user/{userPk}/leave", "123-1234-1210"))

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "SALES_USER")
    void 퇴사_처리는_경리담당자만_접근_가능() throws Exception{
        // when
        mockMvc.perform(delete("/api/user/{userPk}/leave", "fobidden"))

        // then
        .andExpect(status().isForbidden());
    }
}
