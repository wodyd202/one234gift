package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.ChangeCustomerService;
import com.one234gift.customerservice.command.application.ChangeSaleStateService;
import com.one234gift.customerservice.common.APIResponse;
import com.one234gift.customerservice.common.CommandException;
import com.one234gift.customerservice.domain.model.ChangeAddressDetail;
import com.one234gift.customerservice.domain.model.ChangeBusinessName;
import com.one234gift.customerservice.domain.model.ChangeBusinessNumber;
import com.one234gift.customerservice.domain.model.ChangeFax;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/customer/{customerId}")
public class ChangeCustomerAPI {
    @Autowired private ChangeCustomerService changeCustomerService;
    @Autowired private ChangeSaleStateService changeSaleStateService;

    @PutMapping("business-name")
    public APIResponse changeBusinessName(@Valid @RequestBody ChangeBusinessName businessName,
                                          Errors errors,
                                          @PathVariable Long customerId,
                                          Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeBusinessName(customerId, businessName);
        return new APIResponse(customerModel, HttpStatus.OK);
    }

    @PutMapping("address-detail")
    public APIResponse changeAddressDetail(@Valid @RequestBody ChangeAddressDetail addressDetail,
                                           Errors errors,
                                           @PathVariable Long customerId,
                                           Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeAddressDetail(customerId, addressDetail);
        return new APIResponse(customerModel, HttpStatus.OK);
    }

    @PutMapping("business-number")
    public APIResponse changeBusinessNumber(@Valid @RequestBody ChangeBusinessNumber businessNumber,
                                            Errors errors,
                                            @PathVariable Long customerId,
                                            Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeBusinessNumber(customerId, businessNumber);
        return new APIResponse(customerModel, HttpStatus.OK);
    }

    @PutMapping("fax")
    public APIResponse changeFax(@Valid @RequestBody ChangeFax fax,
                                 Errors errors,
                                 @PathVariable Long customerId,
                                 Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeFax(customerId, fax);
        return new APIResponse(customerModel, HttpStatus.OK);
    }

    @PutMapping("sale")
    public APIResponse sale(@PathVariable Long customerId,
                            Principal principal){
        changeSaleStateService.sale(customerId);
        return new APIResponse(HttpStatus.OK);
    }

    @PutMapping("sale-stop")
    public APIResponse saleStop(@PathVariable Long customerId,
                            Principal principal){
        changeSaleStateService.saleStop(customerId);
        return new APIResponse(HttpStatus.OK);
    }

}
