package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.model.ChangePurchasingManager;
import com.one234gift.customerservice.domain.value.Manager;

public class AddedPurcgasubgManager extends ChangedCustomerEvent {
    public AddedPurcgasubgManager(ChangePurchasingManager purchasingManager, Long id, Manager manager) {
        super(id, manager, String.format("[이름=%s, 대표 전화번호=%s]", purchasingManager.getName(), purchasingManager.getContact().getMainTel()));
    }

    @Override
    public String getType() {
        return "담당자 추가";
    }
}
