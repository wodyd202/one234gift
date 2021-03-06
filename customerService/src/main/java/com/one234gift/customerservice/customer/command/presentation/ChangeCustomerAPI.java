package com.one234gift.customerservice.customer.command.presentation;

import com.one234gift.customerservice.customer.command.application.ChangeCustomerService;
import com.one234gift.customerservice.customer.command.application.ChangeSaleStateService;
import com.one234gift.customerservice.common.CommandException;
import com.one234gift.customerservice.customer.command.application.model.*;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
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
        CustomerModel customerModel = changeCustomerService.changeBusinessName(customerId, businessName, getChanger(principal));
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
        CustomerModel customerModel = changeCustomerService.changeAddressDetail(customerId, addressDetail, getChanger(principal));
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
        CustomerModel customerModel = changeCustomerService.changeBusinessNumber(customerId, businessNumber, getChanger(principal));
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
        CustomerModel customerModel = changeCustomerService.changeFax(customerId, fax, getChanger(principal));
        return ResponseEntity.ok(customerModel);
    }

    @PutMapping("sale")
    public ResponseEntity<Void> sale(@PathVariable Long customerId,
                                     Principal principal){
        changeSaleStateService.sale(customerId, getChanger(principal));
        return ResponseEntity.ok(null);
    }

    @PutMapping("sale-stop")
    public ResponseEntity<Void> saleStop(@PathVariable Long customerId,
                                         Principal principal){
        changeSaleStateService.saleStop(customerId, getChanger(principal));
        return ResponseEntity.ok(null);
    }

    @PutMapping("purchasing-manager")
    public ResponseEntity<CustomerModel> addPurchasingManager(@PathVariable Long customerId,
                                                              @Valid @RequestBody ChangePurchasingManager purchasingManager,
                                                              Errors errors,
                                                              Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.addPurchasingManager(customerId, purchasingManager, getChanger(principal));
        return ResponseEntity.ok(customerModel);
    }

    @DeleteMapping("purchasing-manager")
    public ResponseEntity<CustomerModel> removePurchasingManager(@PathVariable Long customerId,
                                                                 @Valid @RequestBody RemovePurchasingManager purchasingManager,
                                                                 Errors errors,
                                                                 Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = changeCustomerService.removePurchasingManager(customerId, purchasingManager, getChanger(principal));
        return ResponseEntity.ok(customerModel);
    }

    private Changer getChanger(Principal principal) {
        return new Changer(principal.getName());
    }

}
