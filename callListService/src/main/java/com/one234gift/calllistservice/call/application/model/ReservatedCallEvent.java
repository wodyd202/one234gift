package com.one234gift.calllistservice.call.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservatedCallEvent {
    private String salesHistoryId;
    private Customer customer;
    private String reserver;
    private LocalDate callReservationDate;

    @Builder
    public ReservatedCallEvent(String salesHistoryId, ReservatedCallEvent.Customer customer, String reserver, LocalDate callReservationDate) {
        this.salesHistoryId = salesHistoryId;
        this.customer = customer;
        this.reserver = reserver;
        this.callReservationDate = callReservationDate;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Customer {
        private long customerId;
        private String category;
        private ReservatedCallEvent.Customer.BusinessInfo businessInfo;

        @Builder
        public Customer(long customerId, String category, ReservatedCallEvent.Customer.BusinessInfo businessInfo) {
            this.customerId = customerId;
            this.category = category;
            this.businessInfo = businessInfo;
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class BusinessInfo {
            private String name;

            @Builder
            public BusinessInfo(String name) {
                this.name = name;
            }
        }
    }
}
