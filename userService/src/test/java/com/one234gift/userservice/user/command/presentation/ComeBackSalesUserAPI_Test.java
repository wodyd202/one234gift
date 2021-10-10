package com.one234gift.userservice.user.command.presentation;

import com.one234gift.userservice.APITest;
import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.user.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.userservice.user.UserTestHelper.persistUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000")
public class ComeBackSalesUserAPI_Test extends APITest {
    @Autowired UserRepository userRepository;

    @Test
    void 재영입() throws Exception {
        User salesUser = UserFixture.aSalesUser("000-1234-0000");
        salesUser.leave();
        persistUser(userRepository, salesUser);

        mockMvc.perform(put("/api/sales-user/{userPk}/comeback","000-1234-0000"))
                .andExpect(status().isOk());
    }
}
