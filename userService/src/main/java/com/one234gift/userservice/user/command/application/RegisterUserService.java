package com.one234gift.userservice.command.application;

import com.one234gift.userservice.command.application.model.RegisterUser;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.exception.AlreadyExistUserException;
import com.one234gift.userservice.domain.read.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * 사원 등록 서비스
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
@Retryable(maxAttempts = 3, value = SQLException.class, backoff = @Backoff(delay = 500))
public class RegisterUserService {
    private UserMapper userMapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * @param registerUser
     * # 사용자 등록
     */
    public UserModel register(RegisterUser registerUser) {
        User user = userMapper.mapFrom(registerUser, passwordEncoder);
        verifyNotExistUser(user);
        userRepository.save(user);
        UserModel userModel = user.toModel();
        log.info("save user into database : {}", userModel);
        return userModel;
    }

    /**
     * @param user
     * # 사용자 존재 여부 확인
     */
    private void verifyNotExistUser(User user){
        if(userRepository.existsByPhone(user.getPhone())){
            throw new AlreadyExistUserException();
        }
    }
}
