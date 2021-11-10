package com.one234gift.customerservice.customer.command.application.event;

import lombok.Getter;

/**
 * 팩스 변경 이벤트
 */
@Getter
public class ChangedFaxEvent extends ChangedCustomerEvent {
    private String fax;
    public ChangedFaxEvent(Long id,String who, String fax) {
        super(id, who);
        this.fax = fax;
    }
}
