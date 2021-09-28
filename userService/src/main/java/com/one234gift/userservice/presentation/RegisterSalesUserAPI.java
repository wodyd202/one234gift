package com.one234gift.userservice.presentation;

import com.one234gift.userservice.application.RegisterSalesUserService;
import com.one234gift.userservice.common.APIResponse;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.one234gift.userservice.common.APIHelper.verifyNotContainsError;

/**
 * 영업사원 생성 API
 * - 경리의 권한을 가진 사용자만 접근 가능
 */
@RestController
@RequestMapping("api/sales-user")
public class RegisterSalesUserAPI {
    @Autowired private RegisterSalesUserService registerSalesUserService;

    @PostMapping
    public APIResponse registerSalesUser(@Valid @RequestBody RegisterUser registerUser, Errors errors){
        verifyNotContainsError(errors);
        UserModel userModel = registerSalesUserService.register(registerUser);
        return new APIResponse(userModel, HttpStatus.OK);
    }
}
