package com.one234gift.happycallservice.presentation.model;

import com.one234gift.happycallservice.domain.read.HappyCallModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class HappyCallModels {
    private List<HappyCallModel> happyCallModels;
    private long totalElement;

    @Builder
    public HappyCallModels(List<HappyCallModel> happyCallModels, long totalElement) {
        this.happyCallModels = happyCallModels;
        this.totalElement = totalElement;
    }
}
