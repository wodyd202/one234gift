package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.command.application.ChangeSalesHistoryService;
import com.one234gift.saleshistoryservice.common.CommandException;
import com.one234gift.saleshistoryservice.command.application.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.command.application.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.command.application.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/sales-history/{salesHistoryId}")
public class ChangeSalesHistoryAPI {
    @Autowired private ChangeSalesHistoryService changeSalesHistoryService;

    @PutMapping("sample")
    public ResponseEntity<SalesHistoryModel> changeSample(@PathVariable long salesHistoryId,
                                       Principal principal){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeSample(salesHistoryId, principal.getName());
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("catalogue")
    public ResponseEntity<SalesHistoryModel> changeCatalogue(@PathVariable long salesHistoryId,
                                       Principal principal){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCatalogue(salesHistoryId, principal.getName());
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("content")
    public ResponseEntity<SalesHistoryModel> changeContent(@PathVariable long salesHistoryId,
                                     @Valid @RequestBody ChangeSalesHistoryContent salesHistoryContent,
                                     Errors errors,
                                     Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeContent(salesHistoryId, salesHistoryContent, principal.getName());
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("call-reservation-date")
    public ResponseEntity<SalesHistoryModel> changeCallReservationDate(@PathVariable long salesHistoryId,
                                                 @Valid @RequestBody ChangeCallReservationDate callReservationDate,
                                                 Errors errors,
                                                 Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCallReservationDate(salesHistoryId, callReservationDate, principal.getName());
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("reactivity")
    public ResponseEntity<SalesHistoryModel> changeReactivity(@PathVariable long salesHistoryId,
                                        @Valid @RequestBody ChangeCustomerReactivity customerReactivity,
                                        Errors errors,
                                        Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeReactivity(salesHistoryId, customerReactivity, principal.getName());
        return ResponseEntity.ok(salesHistoryModel);
    }
}
