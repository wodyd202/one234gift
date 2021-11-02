package com.one234gift.customerservice.query.presentation;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.QueryCustomerService;
import com.one234gift.customerservice.query.application.model.CustomerModels;
import com.one234gift.customerservice.query.application.model.CustomerSearchDTO;
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

    /**
     * @param customerSearchDTO
     * @param pageable
     * # 고객 리스트 가져오기
     */
    @GetMapping
    public ResponseEntity<CustomerModels> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable){
        CustomerModels customerModels = queryCustomerService.findAll(customerSearchDTO, pageable);
        return ResponseEntity.ok(customerModels);
    }

    /**
     * @param pageable
     * @param principal
     * # 담당중인 고객 리스트 가져오기
     */
    @GetMapping("responsible")
    public ResponseEntity<CustomerModels> responsible(Pageable pageable, Principal principal){
        CustomerModels customerModels = queryCustomerService.findByManager(principal.getName(), pageable);
        return ResponseEntity.ok(customerModels);
    }

    /**
     * @param customerId
     * # 고객 상세 정보 가져오기
     */
    @GetMapping("{customerId}")
    public ResponseEntity<CustomerModel> customerModel(@PathVariable Long customerId) {
        CustomerModel customerModel = queryCustomerService.findById(customerId);
        return ResponseEntity.ok(customerModel);
    }

    /**
     * @param customerId
     * # 해당 고객이 존재하는지 체크
     */
    @GetMapping("{customerId}/exist")
    public ResponseEntity<Boolean> existByCustomerId(@PathVariable Long customerId){
        boolean exist = queryCustomerService.existById(customerId);
        return ResponseEntity.ok(exist);
    }
}
