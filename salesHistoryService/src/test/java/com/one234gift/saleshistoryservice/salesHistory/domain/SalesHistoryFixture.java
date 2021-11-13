package com.one234gift.saleshistoryservice.salesHistory.domain;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Employee;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.CustomerReactivity;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;

public class SalesHistoryFixture {
    public static RegisterSalesHistory.RegisterSalesHistoryBuilder aRegisterSalesHistory() {
        return RegisterSalesHistory.builder()
                .customerId(1L)
                .sample(false)
                .catalogue(false)
                .content("영업 내용")
                .reactivity(CustomerReactivity.THREE);
    }

    public static Employee.EmployeeBuilder aEmployee(){
        return Employee.builder()
                .name("고객 담당자")
                .phone("휴대폰번호");
    }

    public static Writer aWriter(String writer){
        return Writer.builder().name(writer).phone(writer).build();
    }
}
