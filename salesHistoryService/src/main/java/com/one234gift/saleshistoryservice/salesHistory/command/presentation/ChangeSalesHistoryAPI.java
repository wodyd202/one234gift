package com.one234gift.saleshistoryservice.salesHistory.command.presentation;

import com.one234gift.saleshistoryservice.salesHistory.command.application.ChangeSalesHistoryService;
import com.one234gift.saleshistoryservice.common.CommandException;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;
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
    public ResponseEntity<SalesHistoryModel> changeSample(@PathVariable SalesHistoryId salesHistoryId,
                                                          Principal principal){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeSample(salesHistoryId, getWriter(principal));
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("catalogue")
    public ResponseEntity<SalesHistoryModel> changeCatalogue(@PathVariable SalesHistoryId salesHistoryId,
                                                             Principal principal){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCatalogue(salesHistoryId, getWriter(principal));
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("content")
    public ResponseEntity<SalesHistoryModel> changeContent(@PathVariable SalesHistoryId salesHistoryId,
                                                         @Valid @RequestBody ChangeSalesHistoryContent salesHistoryContent,
                                                         Errors errors,
                                                         Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeContent(salesHistoryId, salesHistoryContent, getWriter(principal));
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("call-reservation-date")
    public ResponseEntity<SalesHistoryModel> changeCallReservationDate(@PathVariable SalesHistoryId salesHistoryId,
                                                                     @Valid @RequestBody ChangeCallReservationDate callReservationDate,
                                                                     Errors errors,
                                                                     Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCallReservationDate(salesHistoryId, callReservationDate, getWriter(principal));
        return ResponseEntity.ok(salesHistoryModel);
    }

    @PutMapping("reactivity")
    public ResponseEntity<SalesHistoryModel> changeReactivity(@PathVariable SalesHistoryId salesHistoryId,
                                                                @Valid @RequestBody ChangeCustomerReactivity customerReactivity,
                                                                Errors errors,
                                                                Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeReactivity(salesHistoryId, customerReactivity, getWriter(principal));
        return ResponseEntity.ok(salesHistoryModel);
    }

    private Writer getWriter(Principal principal){
        return Writer.builder().phone(principal.getName()).build();
    }
}
