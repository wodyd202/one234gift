package com.one234gift.customerservice.customer.query.application.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 영업 기록 리스트 모델
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesHistoryModels {
    private List<SalesHistoryModel> salesHistoryModels;
    private long totalElement;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SalesHistoryModel {
        // 영업 기록 고유 번호
        private Long salesHistoryId;

        // 영업 기록 내용
        private String content;

        // 영업 기록 생성일
        private LocalDateTime createDateTime;

        // 영업 기록 작성자
        private Writer writer;

        /**
         * 작성자 정보
         */
        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Writer {
            private String phone;
            private String name;
        }
    }
}
