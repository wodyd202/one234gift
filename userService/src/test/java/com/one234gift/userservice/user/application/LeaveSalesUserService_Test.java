package com.one234gift.userservice.user.application;

import com.one234gift.userservice.application.LeaveSalesUserService;
import com.one234gift.userservice.application.UserRepository;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.user.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.userservice.application.UserServiceHelper.findByPhone;
import static com.one234gift.userservice.domain.value.State.LEAVE;
import static com.one234gift.userservice.user.UserTestHelper.persistUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LeaveSalesUserService_Test {
    @Autowired LeaveSalesUserService leaveSalesUserService;
    @Autowired UserRepository userRepository;

    @Test
    void 영업_담당자_퇴사(){
        persistUser(userRepository, UserFixture.aSalesUser("000-0000-0000"));

        Phone userPk = new Phone("000-0000-0000");
        leaveSalesUserService.leave(userPk);
        UserModel user = findByPhone(userRepository, userPk).toModel();
        assertEquals(user.getState(), LEAVE);
    }
}
