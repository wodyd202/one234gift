package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.read.PurchasingManagerModel;
import com.one234gift.customerservice.domain.value.Manager;

public class RemovedPurcgasubgManager extends ChangedCustomerEvent {

    public RemovedPurcgasubgManager(PurchasingManagerModel purchasingManagerModel, Long id, Manager manager) {
        super(id, manager, String.format("[이름=%s, 대표 전화번호=%s]", purchasingManagerModel.getName(), purchasingManagerModel.getContact().getMainTel()));
    }

    @Override
    public String getType() {
        return "담당자 삭제";
    }
}
