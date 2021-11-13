package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.application.model.RegisterCallReservation;
import com.one234gift.happycallservice.domain.CallReservation;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.Reserver;
import org.springframework.stereotype.Component;

@Component
public class HappyCallMapper {
    public CallReservation mapFrom(RegisterCallReservation registerCallReservation) {
        RegisterCallReservation.Customer customer = registerCallReservation.getCustomer();
        return CallReservation.builder()
                .reserver(new Reserver(registerCallReservation.getReserver()))
                .when(registerCallReservation.getCallReservationDate())
                .targetCustomer(CustomerInfo.builder()
                        .name(customer.getBusinessInfo().getName())
                        .category(customer.getCategory())
                        .build())
                .build();
    }
}
