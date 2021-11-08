package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.command.application.RegisterSalesHistoryService;
import com.one234gift.saleshistoryservice.common.CommandException;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/sales-history")
public class RegisterSalesHistoryAPI {
    @Autowired private RegisterSalesHistoryService registerSalesHistoryService;

    @PostMapping
    public ResponseEntity<SalesHistoryModel> register(@Valid @RequestBody RegisterSalesHistory registerSalesHistory, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = registerSalesHistoryService.register(registerSalesHistory);
        return ResponseEntity.ok(salesHistoryModel);
    }
}
