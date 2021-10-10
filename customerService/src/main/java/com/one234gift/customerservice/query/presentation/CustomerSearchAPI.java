package com.one234gift.customerservice.query.presentation;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.QueryCustomerService;
import com.one234gift.customerservice.query.model.CustomerSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerSearchAPI {
    @Autowired private QueryCustomerService queryCustomerService;

    @GetMapping
    public ResponseEntity<List<CustomerModel>> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable){
        List<CustomerModel> customerModels = queryCustomerService.findAll(customerSearchDTO, pageable);
        return ResponseEntity.ok(customerModels);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerModel> customerModel(@PathVariable Long customerId){
        CustomerModel customerModel = queryCustomerService.findById(customerId);
        return ResponseEntity.ok(customerModel);
    }

    @GetMapping("{customerId}/exist")
    public ResponseEntity<Boolean> existByCustomerId(@PathVariable Long customerId){
        boolean exist = queryCustomerService.existById(customerId);
        return ResponseEntity.ok(exist);
    }
}
