package com.one234gift.customerservice.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class Customer_Test {

    @Test(expected = IllegalArgumentException.class)
    public void 분류_미입력() {
        new Category("");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "한글 ",
            "한 글",
            " 한글",
            "한글1"
    })
    public void 분류는_한글조합_1자_이상_15자_이하만_허용(String category) {
        assertThrows(IllegalArgumentException.class,()->{
            new Category(category);
        });
    }

    @Test
    public void 분류_입력(){
        Category category = new Category("은행");
        assertEquals(category ,new Category("은행"));
        assertEquals(category.get(), "은행");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 고객정보중_고객명_미입력(){
        BusinessInfo.builder()
                .name(new BusinessName(""))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 고객정보중_고객명은_한글조합_3자_이상_30자_이하_공백_허용_로_입력해야함(){
        BusinessInfo.builder()
                .name(new BusinessName("invalid"))
                .build();
    }

    @Test
    public void 고객정보중_사업자등록번호는_필수_입력은_아님(){
        BusinessInfo businessInfo = BusinessInfo.builder()
                .name(new BusinessName("은행"))
                .build();
        BusinessInfoModel businessInfoModel = businessInfo.toModel();
        assertNull(businessInfoModel.getNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void 사업자번호입력시_양식에_맞게_입력해야함(){
        BusinessInfo.builder()
                .name(new BusinessName("은행"))
                .number(new BusinessNumber("0000000000"))
                .build();
    }

    @Test
    public void 사업자번호_입력(){
        BusinessInfoModel businessInfo = BusinessInfo.builder()
                .name(new BusinessName("은행"))
                .number(new BusinessNumber("000-00-00000"))
                .build().toModel();
        assertEquals(businessInfo.getName(), "은행");
        assertEquals(businessInfo.getNumber(), "000-00-00000");
    }

    @Test
    public void 구매담당자_이름_입력(){
        ManagerName managerName = new ManagerName("구매담당자");
        assertEquals(managerName.get(), "구매담당자");
        assertEquals(managerName, new ManagerName("구매담당자"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "홍길동 ",
            " 홍길동",
            "홍 길동",
            "홍길동1"
    })
    public void 구매담당자는_이름은_한글조합_1자이상_10자_이하만_허용(String managerName){
        assertThrows(IllegalArgumentException.class, ()->{
            new ManagerName(managerName);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "직위 ",
            " 직위",
            "직 위",
            "직위1"
    })
    public void 구매담당자_직위는_한글조합_1자이상_10자_이하만_허용(String jobTitle){
        assertThrows(IllegalArgumentException.class, ()->{
           new JobTitle(jobTitle);
        });
    }

    @Test
    public void 구매담당자_직위_입력(){
        JobTitle jobTitle = new JobTitle("직위");
        assertEquals(jobTitle, new JobTitle("직위"));
        assertEquals(jobTitle.get(), "직위");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid"
    })
    public void 구매담당자_이메일은_이메일_형식으로_입력해야함(String email){
        assertThrows(IllegalArgumentException.class,()->{
            new Email(email);
        });
    }

    @Test
    public void 이메일_입력(){
        Email email = new Email("test@naver.com");
        assertEquals(email, new Email("test@naver.com"));
        assertEquals(email.get(), "test@naver.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 구매담당자입력시_이름_미입력(){
        ChangePurchasingManager changePurchasingManager = ChangePurchasingManager.builder()
                .build();
        new PurchasingManager(changePurchasingManager);
    }

    @Test
    public void 구매담당자입력시_이메일_직위는_입력하지_않아도됨(){
        ChangePurchasingManager changePurchasingManager = ChangePurchasingManager.builder()
                .name("구매담당자")
                .build();
        PurchasingManager purchasingManager = new PurchasingManager(changePurchasingManager);
        PurchasingManagerModel purchasingManagerModel = purchasingManager.toModel();
        assertEquals(purchasingManagerModel.getName(), "구매담당자");
        assertNull(purchasingManagerModel.getEmail());
        assertNull(purchasingManagerModel.getJobTitle());
    }

    @Test
    public void 구매담당자_입력(){
        ChangePurchasingManager changePurchasingManager = ChangePurchasingManager.builder()
                .jobTitle("팀장")
                .name("구매담당자")
                .email("test@google.com")
                .build();
        PurchasingManager purchasingManager = new PurchasingManager(changePurchasingManager);
        PurchasingManagerModel purchasingManagerModel = purchasingManager.toModel();
        assertEquals(purchasingManagerModel.getJobTitle(), "팀장");
        assertEquals(purchasingManagerModel.getEmail(), "test@google.com");
        assertEquals(purchasingManagerModel.getName(), "구매담당자");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 잘못된_형식의_연락처(){
        new Tel("00000000000");
    }

    @Test
    public void 연락처_입력(){
        Tel tel = new Tel("000-0000-0000");
        assertEquals(tel, new Tel("000-0000-0000"));
        assertEquals(tel.get(), "000-0000-0000");
    }

    @Test
    public void 연락처_그룹_입력(){
        ChangeContact changeContact = ChangeContact.builder()
                .mainTel("000-0000-0000")
                .subTel("000-0000-0000")
                .fax("00-000-0000")
                .build();
        Contact contact = new Contact(changeContact);
        ContactModel contactModel = contact.toModel();
        assertEquals(contactModel.getMainTel(), "000-0000-0000");
        assertEquals(contactModel.getSubTel(), "000-0000-0000");
        assertEquals(contactModel.getFax(), "00-000-0000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 대표_연락처_미입력(){
        ChangeContact changeContact = ChangeContact.builder()
                .build();
        new Contact(changeContact);
    }

    @Test
    public void 지역_입력(){
        Location location = new Location("서울특별시");
        assertEquals(location, new Location("서울특별시"));
        assertEquals(location.get(), "서울특별시");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!@#",
            "서울!@# "
    })
    public void 지역은_한글및_숫자_영어조합_1자이상_15자이하로_입력해야함(String location){
        assertThrows(IllegalArgumentException.class,()->{
            new Location(location);
        });
    }

    @Test
    public void 주소_상세_입력(){
        AddressDetail addressDetail = new AddressDetail("주소 상세");
        assertEquals(addressDetail, new AddressDetail("주소 상세"));
        assertEquals(addressDetail.get(), "주소 상세");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!@#",
            "주소 상세#@!@"
    })
    public void 주소_상세는_한글_숫자_영어조합_공백포함_1자이상_20자이하로입력해야함(String detail){
        assertThrows(IllegalArgumentException.class,()->{
            new AddressDetail(detail);
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void 지역_미입력(){
        new Location("");
    }

    @Test
    public void 주소_입력(){
        ChangeAddress changeAddress = ChangeAddress.builder()
                .location("서울특별시")
                .addressDetail("주소 상세")
                .build();
        Address address = new Address(changeAddress);
        AddressModel addressModel = address.toModel();
        assertEquals(addressModel.getLocation(), "서울특별시");
        assertEquals(addressModel.getAddressDetail(), "주소 상세");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 주소입력시_지역을반드시_입력해야함(){
        ChangeAddress changeAddress = ChangeAddress.builder().build();
        new Address(changeAddress);
    }

    @Test
    public void 주소입력시_주소_상세는_입력하지않아도됨(){
        ChangeAddress changeAddress = ChangeAddress.builder()
                .location("서울특별시")
                .build();
        Address address = new Address(changeAddress);
        AddressModel addressModel = address.toModel();
        assertEquals(addressModel.getLocation(), "서울특별시");
        assertNull(addressModel.getAddressDetail());
    }

    @Test(expected = IllegalArgumentException.class)
    void 비고는_특수문자를_허용하지_않고_1자이상_100자이하로_입력해야함(){

    }

    @Test
    void 비고_입력(){
        Note note = new Note("비고");
        assertEquals(note, new Note("비고"));
        assertEquals(note.get(), "비고");
    }

}
