package com.one234gift.customerservice.customer.command.infrastructure.model;

import com.one234gift.customerservice.customer.command.application.event.*;
import lombok.Getter;

@Getter
public class CustomerHistory {
    private long customerId;
    private String payload;
    private String type;
    private String who;

    public CustomerHistory(Long id) {
        this.customerId = id;
    }

    protected void on(AddedPurchasingManagerEvent event){
        on(event.getPurchasingManager().toString(), "구매 담당자 추가", event);
    }

    protected void on(RemovedPurchasingManagerEvent event){
        on(Long.toString(event.getRemovePurchasingManagerId()), "구매 담당자 삭제", event);
    }

    protected void on(ChangedAddressDetailEvent event){
        on(event.getAddressDetail(), "상세 주소 변경", event);
    }

    protected void on(ChangedBusinessNameEvent event){
        on(event.getBusinessName(), "고객 업체명 변경", event);
    }

    protected void on(ChangedBusinessNumberEvent event){
        on(event.getBusinessNumber(), "사업자 번호 변경", event);
    }

    protected void on(ChangedFaxEvent event){
        on(event.getFax(), "팩스 번호 변경", event);
    }

    protected void on(ChangedStateEvent event){
        on(event.getState().toString(), "상태 변경", event);
    }

    private void on(String payload, String type, ChangedCustomerEvent event){
        this.payload = payload;
        this.type = type;
        who = event.getWho();
    }
}
