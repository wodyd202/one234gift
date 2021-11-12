package com.one234gift.orderservice.order.query.application.model;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderModels {
    private List<OrderModel> orderModels;
    private long totalElement;
    private long totalPage;
    private boolean prev;
    private boolean next;
    private Pageable pageable;

    @Builder
    public OrderModels(List<OrderModel> orderModels, long totalElement, Pageable pageable) {
        this.orderModels = orderModels;
        this.totalElement = totalElement;
        this.pageable = pageable;
        this.totalPage = (long) Math.ceil((double)totalElement / pageable.getSize());
        this.prev = !(totalPage == 0 || pageable.getPage() == 0);
        this.next = pageable.getPage() < totalPage - 1;
    }
}
