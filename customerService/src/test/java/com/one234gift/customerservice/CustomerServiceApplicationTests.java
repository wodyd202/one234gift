package com.one234gift.customerservice;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CustomerServiceApplicationTests {

    @Test
    void contextLoads() {
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(2021, 10, 10);
        System.out.println(of.isBefore(now));
    }

}
