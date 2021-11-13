package com.one234gift.saleshistoryservice.salesHistory.command.application.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private long customerId;
    private String category;
    private BusinessInfo businessInfo;
    private SaleState saleState;

    public boolean isSale() {
        return SaleState.SALE.equals(saleState);
    }

    public enum SaleState {
        SALE, STOP
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BusinessInfo {
        private String name;
    }
}
