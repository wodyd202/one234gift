package com.one234gift.customerservice.query.presentation;

import com.one234gift.customerservice.common.CommandException;
import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.QueryCustomerService;
import com.one234gift.customerservice.query.application.model.CustomerModels;
import com.one234gift.customerservice.query.application.model.CustomerSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * 고객 조회 API
 */
@RestController
@RequestMapping("api/customer")
public class CustomerSearchAPI {
    @Autowired private QueryCustomerService queryCustomerService;

    /**
     * # 고객 상세 정보 가져오기
     */
    @GetMapping("{customerId}")
    public ResponseEntity<CustomerModel> customerModel(@PathVariable Long customerId) {
        CustomerModel customerModel = queryCustomerService.getCustomerModel(customerId);
        return ResponseEntity.ok(customerModel);
    }

    /**
     * # 고객 리스트 가져오기
     */
    @GetMapping
    public ResponseEntity<CustomerModels> findAl(@Valid CustomerSearchDTO customerSearchDTO, Errors errors, Pageable pageable){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModels customerModels = queryCustomerService.getCustomerModels(customerSearchDTO, pageable);
        return ResponseEntity.ok(customerModels);
    }

    /**
     * # 담당중인 고객 리스트 가져오기
     */
    @GetMapping("responsible")
    public ResponseEntity<CustomerModels> responsible(@Valid CustomerSearchDTO customerSearchDTO, Errors errors, Pageable pageable, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModels customerModels = queryCustomerService.getCustomerModelsByTargetResponsibleUser(customerSearchDTO, principal.getName(), pageable);
        return ResponseEntity.ok(customerModels);
    }

    /**
     * # 해당 고객이 존재하는지 체크
     */
    @GetMapping("{customerId}/exist")
    public ResponseEntity<Boolean> existByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(queryCustomerService.existCustomer(customerId));
    }
}
