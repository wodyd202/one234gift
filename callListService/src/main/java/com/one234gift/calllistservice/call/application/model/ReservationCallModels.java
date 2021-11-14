package com.one234gift.calllistservice.call.application.model;

import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReservationCallModels {
    private List<ReservationCallModel> reservationCallModels;
    private long totalElement;
    private long totalPage;
    private boolean prev;
    private boolean next;
    private Pageable pageable;

    @Builder
    public ReservationCallModels(List<ReservationCallModel> reservationCallModels, long totalElement, Pageable pageable) {
        this.reservationCallModels = reservationCallModels;
        this.totalElement = totalElement;
        this.pageable = pageable;
        this.totalPage = (long) Math.ceil((double)totalElement / pageable.getSize());
        this.prev = !(totalPage == 0 || pageable.getPage() == 0);
        this.next = pageable.getPage() < totalPage - 1;
    }
}
