package com.one234gift.customerservice.query.application.external;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 영업 기록 모델
 * # 외부 모듈로 부터 받아오는 영업 기록 데이터
 */
@Getter
@Setter
public class SalesHistoryModel {
    // 영업 기록 고유 번호
    private Long salesHistoryId;

    // 영업 기록 내용
    private String content;

    // 영업 기록 생성일
    private LocalDateTime createDateTime;

    // 영업 기록 작성자
    private WriterModel writer;

    /**
     * 작성자 정보
     */
    @Getter
    @Setter
    public static class WriterModel {
        // 작성자 고유 아이디(휴대폰 번호)
        private String phone;

        // 작성자 이름
        private String name;
    }
}
