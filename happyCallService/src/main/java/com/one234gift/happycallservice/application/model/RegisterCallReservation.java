package com.one234gift.happycallservice.application.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterCallReservation {
    private String reserver;
    private long salesHistoryId;
    private Customer customer;
    private LocalDate callReservationDate;

    @Getter
    public static class Customer {
        private String category;
        private BusinessInfo businessInfo;

        @Getter
        public static class BusinessInfo {
            private String name;
        }
    }
}
