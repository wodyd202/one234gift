package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.command.application.ChangeSalesHistoryService;
import com.one234gift.saleshistoryservice.common.APIResponse;
import com.one234gift.saleshistoryservice.common.CommandException;
import com.one234gift.saleshistoryservice.domain.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/sales-history/{salesHistoryId}")
public class ChangeSalesHistoryAPI {
    @Autowired private ChangeSalesHistoryService changeSalesHistoryService;

    @PutMapping("sample")
    public APIResponse changeSample(@PathVariable long salesHistoryId,
                                    Principal principal){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeSample(salesHistoryId, principal.getName());
        return new APIResponse(salesHistoryModel, HttpStatus.OK);
    }

    @PutMapping("catalogue")
    public APIResponse changeCatalogue(@PathVariable long salesHistoryId,
                                       Principal principal){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCatalogue(salesHistoryId, principal.getName());
        return new APIResponse(salesHistoryModel, HttpStatus.OK);
    }

    @PutMapping("content")
    public APIResponse changeContent(@PathVariable long salesHistoryId,
                                     @Valid @RequestBody ChangeSalesHistoryContent salesHistoryContent,
                                     Errors errors,
                                     Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeContent(salesHistoryId, salesHistoryContent, principal.getName());
        return new APIResponse(salesHistoryModel, HttpStatus.OK);
    }

    @PutMapping("call-reservation-date")
    public APIResponse changeCallReservationDate(@PathVariable long salesHistoryId,
                                                 @Valid @RequestBody ChangeCallReservationDate callReservationDate,
                                                 Errors errors,
                                                 Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCallReservationDate(salesHistoryId, callReservationDate, principal.getName());
        return new APIResponse(salesHistoryModel, HttpStatus.OK);
    }

    @PutMapping("reactivity")
    public APIResponse changeReactivity(@PathVariable long salesHistoryId,
                                        @Valid @RequestBody ChangeCustomerReactivity customerReactivity,
                                        Errors errors,
                                        Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeReactivity(salesHistoryId, customerReactivity, principal.getName());
        return new APIResponse(salesHistoryModel, HttpStatus.OK);
    }
}
