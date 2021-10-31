package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.application.exception.CustomerNotFoundException;
import com.one234gift.happycallservice.domain.CallReservation;
import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.OrderHappyCall;
import com.one234gift.happycallservice.domain.model.RegisterHappyCall;
import com.one234gift.happycallservice.domain.model.SalesUser;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HappyCallFactory {
    private CustomerRepository customerRepository;

    public HappyCall newOrderHappyCall(RegisterHappyCall registerHappyCall) {
        CustomerInfo customerInfo = findCustomer(registerHappyCall.getCustomerId());
        SalesUser salesUser = registerHappyCall.getSalesUser();
        return OrderHappyCall.builder()
                .when(registerHappyCall.getWhen())
                .customerInfo(customerInfo)
                .salesUser(new SalesUserInfo(salesUser.getPhone()))
                .build();
    }

    public HappyCall newCallReservation(RegisterHappyCall registerHappyCall) {
        CustomerInfo customerInfo = findCustomer(registerHappyCall.getCustomerId());
        SalesUser salesUser = registerHappyCall.getSalesUser();
        return CallReservation.builder()
                .when(registerHappyCall.getWhen())
                .customerInfo(customerInfo)
                .salesUser(new SalesUserInfo(salesUser.getPhone()))
                .build();
    }

    private CustomerInfo findCustomer(long customerId){
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}
