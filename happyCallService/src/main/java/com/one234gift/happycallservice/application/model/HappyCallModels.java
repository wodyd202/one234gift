package com.one234gift.happycallservice.application.model;

import com.one234gift.happycallservice.common.Pageable;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class HappyCallModels {
    private List<HappyCallModel> happyCallModels;
    private long totalElement;
    private long totalPage;
    private boolean prev;
    private boolean next;
    private Pageable pageable;

    @Builder
    public HappyCallModels(List<HappyCallModel> happyCallModels, long totalElement, Pageable pageable) {
        this.happyCallModels = happyCallModels;
        this.totalElement = totalElement;
        this.pageable = pageable;
        this.totalPage = (long) Math.ceil((double)totalElement / pageable.getSize());
        this.prev = !(totalPage == 0 || pageable.getPage() == 0);
        this.next = pageable.getPage() < totalPage - 1;
    }
}
