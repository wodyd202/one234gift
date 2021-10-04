package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.RemoveSalesHistoryService;
import com.one234gift.customerservice.common.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/sales-history/{salesHistoryId}")
public class RemoveSalesHistoryAPI {
    @Autowired private RemoveSalesHistoryService removeSalesHistoryService;
    @DeleteMapping
    public APIResponse remove(@PathVariable Long salesHistoryId, Principal principal){
        removeSalesHistoryService.remove(salesHistoryId, principal.getName());
        return new APIResponse(HttpStatus.OK);
    }
}
