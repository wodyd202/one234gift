package com.one234gift.userservice.command.presentation;

import com.one234gift.userservice.command.application.LeaveSalesUserService;
import com.one234gift.userservice.common.APIResponse;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sales-user")
public class LeaveSalesUserAPI {
    @Autowired private LeaveSalesUserService leaveSalesUserService;

    @DeleteMapping("{phone}")
    public APIResponse leave(@PathVariable Phone phone){
        leaveSalesUserService.leave(phone);
        return new APIResponse(null, HttpStatus.OK);
    }
}
