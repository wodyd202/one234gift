package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.model.ChangePurchasingManager;
import com.one234gift.customerservice.customer.command.application.model.RegisterCustomer;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.domain.value.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
final public class CustomerMapper {

    /**
     * @param registerCustomer
     * # 요청 데이터를 통한 고객 생성
     */
    public Customer mapFrom(RegisterCustomer registerCustomer) {
        // 고객 생성
        Customer customer = Customer.builder()
                .category(new Category(registerCustomer.getCategory()))
                .businessInfo(new BusinessInfo(registerCustomer.getBusinessInfo()))
                .fax(new Tel(registerCustomer.getFax()))
                .address(new Address(registerCustomer.getAddress()))
                .build();

        // 구매담당자 생성
        List<PurchasingManager> purchasingManagers = registerCustomer.getPurchasingManagers().stream().map(managers -> new PurchasingManager(managers)).collect(Collectors.toList());
        customer.addPurchasingMangers(purchasingManagers);
        return customer;
    }

    /**
     * @param changePurchasingManager
     * # 요청 데이터를 통한 구매담당자 생성
     */
    public PurchasingManager mapFrom(ChangePurchasingManager changePurchasingManager) {
        return new PurchasingManager(changePurchasingManager);
    }
}
