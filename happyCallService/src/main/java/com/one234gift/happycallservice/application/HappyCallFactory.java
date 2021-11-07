package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.application.exception.CustomerNotFoundException;
import com.one234gift.happycallservice.application.external.CustomerRepository;
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

    /**
     * @param registerHappyCall
     * # 주문 해피콜 생성
     */
    public HappyCall newOrderHappyCall(RegisterHappyCall registerHappyCall) {
        CustomerInfo customerInfo = findCustomer(registerHappyCall.getCustomerId());
        SalesUser salesUser = registerHappyCall.getWriter();
        return OrderHappyCall.builder()
                .when(registerHappyCall.getCallReservationDate())
                .customerInfo(customerInfo)
                .salesUser(new SalesUserInfo(salesUser.getPhone()))
                .build();
    }

    /**
     * @param registerHappyCall
     * # 예약콜 생성
     */
    public HappyCall newCallReservation(RegisterHappyCall registerHappyCall) {
        CustomerInfo customerInfo = findCustomer(registerHappyCall.getCustomerId());
        SalesUser salesUser = registerHappyCall.getWriter();
        return CallReservation.builder()
                .when(registerHappyCall.getCallReservationDate())
                .customerInfo(customerInfo)
                .salesUser(new SalesUserInfo(salesUser.getPhone()))
                .build();
    }

    private CustomerInfo findCustomer(long customerId){
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}
