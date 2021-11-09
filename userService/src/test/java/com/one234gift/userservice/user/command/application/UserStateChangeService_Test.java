package com.one234gift.userservice.user.command.application;

import com.one234gift.userservice.command.application.UserStateChangeService;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.UserState;
import com.one234gift.userservice.user.UserAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.userservice.command.application.UserServiceHelper.findByPhone;
import static com.one234gift.userservice.domain.value.UserState.LEAVE;
import static com.one234gift.userservice.user.UserFixture.aUser;
import static com.one234gift.userservice.user.UserFixture.leavedUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 사용자 재영입 테스트
 */
public class UserStateChangeService_Test extends UserAPITest {
    @Autowired
    UserStateChangeService userStateChangeService;

    @Test
    void 사원_재영입() {
        // given
        persistUser(leavedUser("010-0000-0004"));

        // when
        Phone phone = new Phone("010-0000-0004");
        userStateChangeService.comeback(phone);
        UserModel user = findByPhone(userRepository, phone).toModel();

        // then
        assertEquals(user.getState(), UserState.WORK);
    }

    @Test
    void 사원_퇴사(){
        // given
        persistUser(aUser("000-0000-0005"));

        // when
        Phone phone = new Phone("000-0000-0005");
        userStateChangeService.leave(phone);
        UserModel user = findByPhone(userRepository, phone).toModel();

        // then
        assertEquals(user.getState(), LEAVE);
    }
}
