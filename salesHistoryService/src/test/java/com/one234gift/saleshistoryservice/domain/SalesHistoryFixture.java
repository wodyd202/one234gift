package com.one234gift.saleshistoryservice.domain;

import com.one234gift.saleshistoryservice.domain.value.Writer;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.value.CustomerReactivity;

public class SalesHistoryFixture {
    public static RegisterSalesHistory.RegisterSalesHistoryBuilder aRegisterSalesHistory() {
        return RegisterSalesHistory.builder()
                .customerId(1L)
                .sample(false)
                .catalogue(false)
                .content("영업 내용")
                .reactivity(CustomerReactivity.THREE);
    }

    public static Writer.WriterBuilder aManager(){
        return Writer.builder()
                .name("고객 담당자")
                .phone("휴대폰번호");
    }
}
