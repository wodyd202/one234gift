package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.RegisterSalesHistoryService;
import com.one234gift.customerservice.common.APIResponse;
import com.one234gift.customerservice.common.CommandException;
import com.one234gift.customerservice.domain.model.RegisterSalesHistory;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/customer/{customerId}/sales-history")
public class RegisterSalesHistoryAPI {
    @Autowired private RegisterSalesHistoryService registerSalesHistoryService;

    @PostMapping
    public APIResponse register(@PathVariable Long customerId, @Valid @RequestBody RegisterSalesHistory registerSalesHistory, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = registerSalesHistoryService.register(customerId, registerSalesHistory);
        return new APIResponse(salesHistoryModel, HttpStatus.OK);
    }
}

