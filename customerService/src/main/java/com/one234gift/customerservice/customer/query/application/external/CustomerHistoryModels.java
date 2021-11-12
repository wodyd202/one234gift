package com.one234gift.customerservice.customer.query.application.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 고객 수정 이력
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerHistoryModels {
    private List<CustomerHistoryModel> customerHistoryModels;
    private long totalElement;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomerHistoryModel {
        private String type;
        private String payload;
        private String who;
        private LocalDateTime createDateTime;
    }
}
