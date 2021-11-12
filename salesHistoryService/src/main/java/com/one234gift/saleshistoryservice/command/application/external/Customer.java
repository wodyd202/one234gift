package com.one234gift.saleshistoryservice.command.application.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private SaleState saleState;

    public boolean isSale() {
        return saleState.equals(SaleState.SALE);
    }

    public enum SaleState {
        SALE, STOP
    }
}
