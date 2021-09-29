package com.one234gift.customerservice.domain;

public class Address {
    private final Location location;
    private AddressDetail addressDetail;

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
}
