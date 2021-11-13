package com.one234gift.happycallservice.domain.read;

import com.one234gift.happycallservice.domain.model.SalesUser;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.Reserver;
import lombok.Getter;

import java.time.LocalDate;

@Getter
abstract public class HappyCallModel {
    protected Long seq;
    protected LocalDate when;
    protected SalesUser salesUser;
    protected CustomerInfoModel targetCustomer;
    protected String type;

    public HappyCallModel(Long seq,
                             LocalDate when,
                             Reserver salesUser,
                             CustomerInfo targetCustomer,
                            String type) {
        this.seq = seq;
        this.when = when;
        this.salesUser = salesUser.toModel();
        this.targetCustomer = targetCustomer.toModel();
        this.type = type;
    }

    @Override
    public String toString() {
        return "HappyCallModel{" +
                "seq=" + seq +
                ", when=" + when +
                ", salesUser=" + salesUser +
                ", targetCustomer=" + targetCustomer +
                '}';
    }
}
