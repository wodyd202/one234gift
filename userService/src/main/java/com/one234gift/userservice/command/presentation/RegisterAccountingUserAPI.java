package com.one234gift.userservice.command.presentation;

import com.one234gift.userservice.command.application.RegisterAccountingUserService;
import com.one234gift.userservice.common.APIResponse;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.one234gift.userservice.common.APIHelper.verifyNotContainsError;

@RestController
@RequestMapping("/api/accounting-user")
public class RegisterAccountingUserAPI {
    @Autowired
    private RegisterAccountingUserService registerAccountingUserService;

    @PostMapping
    public APIResponse register(@Valid @RequestBody RegisterUser registerUser, Errors errors){
        verifyNotContainsError(errors);
        UserModel userModel = registerAccountingUserService.register(registerUser);
        return new APIResponse(userModel, HttpStatus.OK);
    }
}
