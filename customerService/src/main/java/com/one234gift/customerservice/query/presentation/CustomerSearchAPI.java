package com.one234gift.customerservice.query.presentation;

import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.QueryCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
public class CustomerSearchAPI {
    @Autowired private QueryCustomerService queryCustomerService;
    @GetMapping("{customerId}")
    public ResponseEntity<CustomerModel> customerModel(@PathVariable Long customerId){
        CustomerModel customerModel = queryCustomerService.findById(customerId);
        return ResponseEntity.ok(customerModel);
    }
}
