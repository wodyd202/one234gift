package com.one234gift.userservice.query.presentation;

import com.one234gift.userservice.domain.exception.NotAccessUserException;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.query.application.QueryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 사원 조회 API
 */
@RestController
@RequestMapping("api/user")
public class UserSearchAPI {
    @Autowired private QueryUserService queryUserService;

    /**
     * @param phone
     * # 사원 단건 정보 조회
     */
    @GetMapping("{phone}")
    public ResponseEntity<UserModel> getUserModel(@PathVariable String phone){
        try {
            UserModel userModel = queryUserService.getUserModel(phone);
            return ResponseEntity.ok(userModel);
        } catch (NotAccessUserException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping
    public ResponseEntity<UserModel> findByPhone(Principal principal){
        UserModel userModel = queryUserService.findByPhone(principal.getName());
        return ResponseEntity.ok(userModel);
    }
}
