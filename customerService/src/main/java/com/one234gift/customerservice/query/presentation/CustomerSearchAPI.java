package com.one234gift.customerservice.query.presentation;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.QueryCustomerService;
import com.one234gift.customerservice.query.model.CustomerModels;
import com.one234gift.customerservice.query.model.CustomerSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/customer")
public class CustomerSearchAPI {
    @Autowired private QueryCustomerService queryCustomerService;

    @GetMapping
    public ResponseEntity<CustomerModels> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable){
        CustomerModels customerModels = queryCustomerService.findAll(customerSearchDTO, pageable);
        return ResponseEntity.ok(customerModels);
    }

    @GetMapping("responsible")
    public ResponseEntity<CustomerModels> responsible(Pageable pageable, Principal principal){
        CustomerModels customerModels = queryCustomerService.findMy(principal.getName(), pageable);
        return ResponseEntity.ok(customerModels);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerModel> customerModel(@PathVariable Long customerId) {
        CustomerModel customerModel = queryCustomerService.findById(customerId);
        return ResponseEntity.ok(customerModel);
    }

    @GetMapping("{customerId}/exist")
    public ResponseEntity<Boolean> existByCustomerId(@PathVariable Long customerId){
        boolean exist = queryCustomerService.existById(customerId);
        return ResponseEntity.ok(exist);
    }
}
