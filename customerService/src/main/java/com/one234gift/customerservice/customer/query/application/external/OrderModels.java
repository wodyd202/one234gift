package com.one234gift.customerservice.customer.query.application.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderModels {
    private List<OrderModel> orderModels;
    private long totalElement;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderModel {
        private Long id;
        private String product;
        private SalesUserModel salesUser;
        private long quantity, salePrice;
        private LocalDateTime createDateTime;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class SalesUserModel {
            private String phone,name;
        }
    }
}
