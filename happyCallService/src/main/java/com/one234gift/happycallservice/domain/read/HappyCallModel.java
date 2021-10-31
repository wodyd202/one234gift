package com.one234gift.happycallservice.domain.read;

import com.one234gift.happycallservice.domain.model.SalesUser;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
abstract public class HappyCallModel {
    protected Long seq;
    protected LocalDate when;
    protected SalesUser salesUser;
    protected CustomerInfoModel targetCustomer;
    protected boolean read;

    public HappyCallModel(Long seq,
                             LocalDate when,
                             SalesUserInfo salesUser,
                             CustomerInfo targetCustomer,
                             boolean read) {
        this.seq = seq;
        this.when = when;
        this.salesUser = salesUser.toModel();
        this.targetCustomer = targetCustomer.toModel();
        this.read = read;
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
