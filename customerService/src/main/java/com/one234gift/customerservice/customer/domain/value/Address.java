package com.one234gift.customerservice.customer.domain.value;

import com.one234gift.customerservice.customer.domain.read.AddressModel;
import com.one234gift.customerservice.customer.command.application.model.ChangeAddress;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
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
        if(changeAddress.getAddressDetail() != null){
            setAddressDetail(new AddressDetail(changeAddress.getAddressDetail()));
        }else{
            setAddressDetail(null);
        }
    }

    private void locationValidation(String location) {
        if(location == null){
            throw new IllegalArgumentException("지역을 입력해주세요.");
        }
    }

    /**
     * @param addressDetail
     * # 상세 주소 변경
     */
    public void changeAddressDetail(AddressDetail addressDetail) {
        setAddressDetail(addressDetail);
    }

    private void setAddressDetail(AddressDetail addressDetail) {
        if(addressDetail == null){
            this.addressDetail = AddressDetail.getInstance();
            return;
        }
        this.addressDetail = addressDetail;
    }

    public AddressModel toModel() {
        return AddressModel.builder()
                .location(location.get())
                .addressDetail(getAddressDetail().get())
                .build();
    }

    public Location getLocation() {
        return location;
    }

    private AddressDetail getAddressDetail() {
        return addressDetail != null ? addressDetail : new AddressDetail();
    }
}
