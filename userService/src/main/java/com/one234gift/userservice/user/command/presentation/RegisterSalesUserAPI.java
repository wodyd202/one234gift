package com.one234gift.userservice.command.presentation;

import com.one234gift.userservice.command.application.RegisterUserService;
import com.one234gift.userservice.command.application.model.RegisterUser;
import com.one234gift.userservice.domain.read.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.one234gift.userservice.common.APIHelper.verifyNotContainsError;


/**
 * 사원 생성 API
 */
@RestController
@RequestMapping("api/user")
public class RegisterSalesUserAPI {
    @Autowired private RegisterUserService registerUserService;

    /**
     * @param registerUser
     * @param errors
     * # 사원 생성
     */
    @PostMapping
    public ResponseEntity<UserModel> registerSalesUser(@Valid @RequestBody RegisterUser registerUser, Errors errors){
        verifyNotContainsError(errors);
        UserModel userModel = registerUserService.register(registerUser);
        return ResponseEntity.ok(userModel);
    }
}
