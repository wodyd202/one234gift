package com.one234gift.userservice.command.application.util;

import com.one234gift.userservice.command.application.exception.DuplicatePhoneException;
import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.stereotype.Component;

@Component
public class SimpleRegisterUserValidator implements RegisterUserValidator {
    private final UserRepository userRepository;

    public SimpleRegisterUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validation(Phone phone) {
        if(existUser(phone)){
            throw new DuplicatePhoneException("해당 전화번호를 사용중인 사용자가 이미 존재합니다. 다른 전화번호를 입력해주세요.");
        }
    }

    private boolean existUser(Phone phone) {
        return userRepository.existByPhone(phone);
    }
}
