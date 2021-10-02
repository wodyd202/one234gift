package com.one234gift.userservice.query.presentation;

import com.one234gift.userservice.common.APIResponse;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.query.application.QueryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;

@RestController
@RequestMapping("api/user")
public class UserSearchAPI {
    @Autowired private QueryUserService queryUserService;

    @GetMapping
    public ResponseEntity<UserModel> findByPhone(Principal principal){
        UserModel userModel = queryUserService.findByPhone(principal.getName());
        return ResponseEntity.ok(userModel);
    }
}
