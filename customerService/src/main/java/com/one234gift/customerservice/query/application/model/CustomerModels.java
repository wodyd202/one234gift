package com.one234gift.customerservice.query.model;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomerModels {
    private List<CustomerModel> customers;
    private long totalElement;
    private long totalPage;
    private boolean prev;
    private boolean next;
    private Pageable pageable;

    @Builder
    public CustomerModels(List<CustomerModel> customers, long totalElement, Pageable pageable) {
        this.customers = customers;
        this.totalElement = totalElement;
        this.pageable = pageable;
        this.totalPage = (long) Math.ceil((double)totalElement / pageable.getSize());
        this.prev = !(totalPage == 0 || pageable.getPage() == 0);
        this.next = pageable.getPage() < totalPage - 1;
    }
}
