package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.ChangeCustomerService;
import com.one234gift.customerservice.command.application.ChangeSaleStateService;
import com.one234gift.customerservice.common.CommandException;
import com.one234gift.customerservice.domain.model.*;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomerModel> changeBusinessName(@Valid @RequestBody ChangeBusinessName businessName,
                                             Errors errors,
                                             @PathVariable Long customerId,
                                             Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeBusinessName(customerId, businessName);
        return ResponseEntity.ok(customerModel);
    }

    @PutMapping("address-detail")
    public ResponseEntity<CustomerModel> changeAddressDetail(@Valid @RequestBody ChangeAddressDetail addressDetail,
                                           Errors errors,
                                           @PathVariable Long customerId,
                                           Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeAddressDetail(customerId, addressDetail);
        return ResponseEntity.ok(customerModel);
    }

    @PutMapping("business-number")
    public ResponseEntity<CustomerModel> changeBusinessNumber(@Valid @RequestBody ChangeBusinessNumber businessNumber,
                                            Errors errors,
                                            @PathVariable Long customerId,
                                            Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeBusinessNumber(customerId, businessNumber);
        return ResponseEntity.ok(customerModel);
    }

    @PutMapping("fax")
    public ResponseEntity<CustomerModel> changeFax(@Valid @RequestBody ChangeFax fax,
                                 Errors errors,
                                 @PathVariable Long customerId,
                                 Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.changeFax(customerId, fax);
        return ResponseEntity.ok(customerModel);
    }

    @PutMapping("sale")
    public ResponseEntity<Void> sale(@PathVariable Long customerId,
                            Principal principal){
        changeSaleStateService.sale(customerId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("sale-stop")
    public ResponseEntity<Void> saleStop(@PathVariable Long customerId,
                            Principal principal){
        changeSaleStateService.saleStop(customerId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("purchasing-manager")
    public ResponseEntity<CustomerModel> addPurchasingManager(@PathVariable Long customerId,
                                                              @Valid @RequestBody ChangePurchasingManager purchasingManager,
                                                              Errors errors){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.addPurchasingManager(customerId, purchasingManager);
        return ResponseEntity.ok(customerModel);
    }

    @DeleteMapping("purchasing-manager")
    public ResponseEntity<CustomerModel> removePurchasingManager(@PathVariable Long customerId,
                                                                 @Valid @RequestBody RemovePurchasingManager purchasingManager,
                                                                 Errors errors){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.removePurchasingManager(customerId, purchasingManager);
        return ResponseEntity.ok(customerModel);
    }

}
