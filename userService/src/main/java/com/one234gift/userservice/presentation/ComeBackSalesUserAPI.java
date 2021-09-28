package com.one234gift.userservice.presentation;

import com.one234gift.userservice.application.ComeBackSalesUserService;
import com.one234gift.userservice.common.APIResponse;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sales-user")
public class ComeBackSalesUserAPI {
    @Autowired private ComeBackSalesUserService comeBackSalesUserService;

    @PutMapping("{phone}/comeback")
    public APIResponse comeback(@PathVariable Phone phone){
        comeBackSalesUserService.comeback(phone);
        return new APIResponse(null, HttpStatus.OK);
    }
}
