package com.one234gift.userservice.command.application;

import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static com.one234gift.userservice.command.application.UserServiceHelper.findByPhone;

/**
 * 사용자 상태 변경 서비스
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
@Retryable(maxAttempts = 3, value = SQLException.class, backoff = @Backoff(delay = 500))
public class UserStateChangeService {
    private UserRepository userRepository;

    /**
     * @param phone
     * # 재영입
     */
    public UserModel comeback(Phone phone) {
        User user = findByPhone(userRepository, phone);
        user.comeBack();
        userRepository.save(user);
        UserModel userModel = user.toModel();
        log.info("comback user : {}", userModel);
        return userModel;
    }

    /**
     * @param phone
     * # 퇴사
     */
    public UserModel leave(Phone phone) {
        User user = findByPhone(userRepository, phone);
        user.leave();
        userRepository.save(user);
        UserModel userModel = user.toModel();
        log.info("leave user : {}", userModel);
        return userModel;
    }
}
