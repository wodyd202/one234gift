package com.one234gift.userservice.command.presentation;

import com.one234gift.userservice.command.application.UserStateChangeService;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 사원 상태 변경 API
 */
@RestController
@RequestMapping("api/user/{phone}")
public class UserChangeStateAPI {
    @Autowired private UserStateChangeService userStateChangeService;

    /**
     * @param phone
     * # 사원 재영입
     */
    @PutMapping("comeback")
    public ResponseEntity<UserModel> comeback(@PathVariable Phone phone){
        UserModel userModel = userStateChangeService.comeback(phone);
        return ResponseEntity.ok(userModel);
    }

    /**
     * @param phone
     * # 사원 퇴사
     */
    @DeleteMapping("leave")
    public ResponseEntity<UserModel> leave(@PathVariable Phone phone){
        UserModel userModel = userStateChangeService.leave(phone);
        return ResponseEntity.ok(userModel);
    }
}
