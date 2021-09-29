package com.one234gift.customerservice.domain;

public class Contact {
    private Tel mainTel;
    private Tel subTel;
    private Tel fax;

    public Contact(ChangeContact contact){
        setMainTel(contact.getMainTel());
        setSubTel(contact.getSubTel());
        setFax(contact.getFax());
    }

    private void setMainTel(String mainTel) {
        mainTelValidation(mainTel);
        this.mainTel = new Tel(mainTel);
    }

    private void mainTelValidation(String mainTel) {
        if(mainTel == null){
            throw new IllegalArgumentException("대표 연락처를 입력해주세요.");
        }
    }

    private void setSubTel(String subTel) {
        if(subTel == null){
            this.subTel = new Tel();
        }else{
            this.subTel = new Tel(subTel);
        }
    }

    private void setFax(String fax) {
        if(fax == null){
            this.fax = new Tel();
        }else {
            this.fax = new Tel(fax);
        }
    }

    public ContactModel toModel() {
        return ContactModel.builder()
                .mainTel(mainTel.get())
                .subTel(subTel.get())
                .fax(fax.get())
                .build();
    }
}
