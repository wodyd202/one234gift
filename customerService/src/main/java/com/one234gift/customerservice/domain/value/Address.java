package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.model.ChangeAddressDetail;
import com.one234gift.customerservice.domain.read.AddressModel;
import com.one234gift.customerservice.domain.model.ChangeAddress;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class Address {
    @Embedded
    @AttributeOverride(name = "location", column = @Column(name = "location", length = 15))
    private final Location location;
    @Embedded
    @AttributeOverride(name = "detail", column = @Column(name = "address_detail", length = 20))
    private AddressDetail addressDetail;

    protected Address(){location = null;}

    public Address(ChangeAddress changeAddress) {
        locationValidation(changeAddress.getLocation());
        this.location = new Location(changeAddress.getLocation());
        setAddressDetail(changeAddress.getAddressDetail());
    }

    private void locationValidation(String location) {
        if(location == null){
            throw new IllegalArgumentException("지역을 입력해주세요.");
        }
    }

    public void changeAddressDetail(ChangeAddressDetail addressDetail) {
        setAddressDetail(addressDetail.getDetail());
    }

    private void setAddressDetail(String addressDetail) {
        if(addressDetail == null){
            this.addressDetail = new AddressDetail();
        }else{
            this.addressDetail = new AddressDetail(addressDetail);
        }
    }

    public AddressModel toModel() {
        return AddressModel.builder()
                .location(location.get())
                .addressDetail(addressDetail.get())
                .build();
    }

    public Location getLocation() {
        return location;
    }

}
