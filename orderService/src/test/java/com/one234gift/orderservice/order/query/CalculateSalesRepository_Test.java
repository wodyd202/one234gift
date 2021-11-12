package com.one234gift.orderservice.order.query;

import com.one234gift.orderservice.order.query.infrastructure.JDBCCalculateSalesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class CalculateSalesRepository_Test {
    @Autowired
    JDBCCalculateSalesRepository calculateSalesRepository;

    @Test
    void 해당_영업자의_누적_매출_가져오기(){
        calculateSalesRepository.findCumulativeSalesByUserId("userId", LocalDate.now());
    }

    @Test
    void 전체_영업자의_누적_매출_가져오기(){
        calculateSalesRepository.findCumulativeSales(LocalDate.now());
    }

}
