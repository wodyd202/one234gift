package com.one234gift.customerservice.customer.domain;

import com.one234gift.customerservice.customer.command.application.CategoryRepository;
import com.one234gift.customerservice.customer.command.application.CustomerMapper;
import com.one234gift.customerservice.customer.command.application.LocationRepository;
import com.one234gift.customerservice.customer.command.application.exception.CategoryNotFoundException;
import com.one234gift.customerservice.customer.command.application.exception.LocationNotFoundException;
import com.one234gift.customerservice.customer.command.application.util.SimpleRegisterCustomerValidator;
import com.one234gift.customerservice.customer.domain.exception.AlreadySaleException;
import com.one234gift.customerservice.customer.domain.exception.AlreadySaleStopException;
import com.one234gift.customerservice.customer.command.application.model.*;
import com.one234gift.customerservice.customer.domain.read.AddressModel;
import com.one234gift.customerservice.customer.domain.read.BusinessInfoModel;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.domain.read.PurchasingManagerModel;
import com.one234gift.customerservice.customer.domain.value.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static com.one234gift.customerservice.customer.CustomerFixture.*;
import static com.one234gift.customerservice.customer.domain.value.SaleState.SALE;
import static com.one234gift.customerservice.customer.domain.value.SaleState.STOP;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Customer_Test {

    @Test
    public void 분류_미입력() {
        // when
        assertThrows(IllegalArgumentException.class, ()->{
            new Category("");
        });
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
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new Category(category);
        });
    }

    @Test
    public void 분류_입력(){
        // when
        Category category = new Category("은행");

        // then
        assertEquals(category ,new Category("은행"));
        assertEquals(category.get(), "은행");
    }

    @Test
    public void 고객정보중_고객명_미입력(){
        // given
        ChangeBusinessInfo businessInfo = ChangeBusinessInfo.builder()
                .build();

        // when
        assertThrows(IllegalArgumentException.class, ()->{
            new BusinessInfo(businessInfo);
        });
    }

    @Test
    public void 고객정보중_고객명은_한글조합_3자_이상_30자_이하_공백_허용_로_입력해야함(){
        // when
        ChangeBusinessInfo businessInfo = ChangeBusinessInfo.builder()
                .name("invalid")
                .build();

        // then
        assertThrows(IllegalArgumentException.class, ()->{
            new BusinessInfo(businessInfo);
        });
    }

    @Test
    public void 고객정보중_사업자등록번호는_필수_입력은_아님(){
        // given
        ChangeBusinessInfo businessInfo = ChangeBusinessInfo.builder()
                .name("은행")
                .build();

        // when
        BusinessInfoModel businessInfoModel = new BusinessInfo(businessInfo).toModel();

        // then
        assertNull(businessInfoModel.getNumber());
    }

    @Test
    public void 사업자번호입력시_양식에_맞게_입력해야함(){
        // given
        ChangeBusinessInfo businessInfo = ChangeBusinessInfo.builder()
                .name("은행")
                .number("0000000000")
                .build();

        // then
        assertThrows(IllegalArgumentException.class, ()->{
            new BusinessInfo(businessInfo);
        });
    }

    @Test
    public void 사업자번호_입력(){
        // given
        ChangeBusinessInfo changeBusinessInfo = ChangeBusinessInfo.builder()
                .name("은행")
                .number("000-00-00000")
                .build();

        // when
        BusinessInfoModel businessInfo = new BusinessInfo(changeBusinessInfo).toModel();

        // then
        assertEquals(businessInfo.getName(), "은행");
        assertEquals(businessInfo.getNumber(), "000-00-00000");
    }

    @Test
    public void 구매담당자_이름_입력(){
        // when
        ManagerName managerName = new ManagerName("구매담당자");

        // then
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
        // when
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
        // when
        assertThrows(IllegalArgumentException.class, ()->{
           new JobTitle(jobTitle);
        });
    }

    @Test
    public void 구매담당자_직위_입력(){
        // when
        JobTitle jobTitle = new JobTitle("직위");

        // then
        assertEquals(jobTitle, new JobTitle("직위"));
        assertEquals(jobTitle.get(), "직위");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid"
    })
    public void 구매담당자_이메일은_이메일_형식으로_입력해야함(String email){
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new Email(email);
        });
    }

    @Test
    public void 이메일_입력(){
        // when
        Email email = new Email("test@naver.com");

        // then
        assertEquals(email, new Email("test@naver.com"));
        assertEquals(email.get(), "test@naver.com");
    }

    @Test
    public void 구매담당자입력시_이름_미입력(){
        // given
        ChangePurchasingManager changePurchasingManager = ChangePurchasingManager.builder()
                .build();

        // when
        assertThrows(IllegalArgumentException.class, ()->{
            new PurchasingManager(changePurchasingManager);
        });
    }

    @Test
    public void 구매담당자입력시_이메일_직위는_입력하지_않아도됨(){
        // given
        ChangePurchasingManager changePurchasingManager = ChangePurchasingManager.builder()
                .name("구매담당자")
                .contact(ChangeContact.builder()
                        .mainTel("000-0000-0000")
                        .build())
                .build();

        // when
        PurchasingManager purchasingManager = new PurchasingManager(changePurchasingManager);
        PurchasingManagerModel purchasingManagerModel = purchasingManager.toModel();

        // then
        assertEquals(purchasingManagerModel.getName(), "구매담당자");
        assertNull(purchasingManagerModel.getEmail());
        assertNull(purchasingManagerModel.getJobTitle());
    }

    @Test
    public void 구매담당자_입력(){
        // given
        ChangePurchasingManager changePurchasingManager = ChangePurchasingManager.builder()
                .jobTitle("팀장")
                .name("구매담당자")
                .email("test@google.com")
                .contact(ChangeContact.builder()
                        .mainTel("000-0000-0000")
                        .build())
                .build();

        // when
        PurchasingManager purchasingManager = new PurchasingManager(changePurchasingManager);
        PurchasingManagerModel purchasingManagerModel = purchasingManager.toModel();

        // then
        assertEquals(purchasingManagerModel.getJobTitle(), "팀장");
        assertEquals(purchasingManagerModel.getEmail(), "test@google.com");
        assertEquals(purchasingManagerModel.getName(), "구매담당자");
        assertEquals(purchasingManagerModel.getContact().getMainTel(), "000-0000-0000");
        assertNull(purchasingManagerModel.getContact().getSubTel());
    }

    @Test
    public void 잘못된_형식의_연락처(){
        // when
        assertThrows(IllegalArgumentException.class, ()->{
            new Tel("00000000000");
        });
    }

    @Test
    public void 연락처_입력(){
        // when
        Tel tel = new Tel("000-0000-0000");

        // then
        assertEquals(tel, new Tel("000-0000-0000"));
        assertEquals(tel.get(), "000-0000-0000");
    }

    @Test
    public void 대표_연락처_미입력(){
        // given
        ChangeContact changeContact = ChangeContact.builder()
                .build();

        //when
        assertThrows(IllegalArgumentException.class, ()->{
            new Contact(changeContact);
        });
    }

    @Test
    public void 지역_입력(){
        // when
        Location location = new Location("서울특별시");

        // then
        assertEquals(location, new Location("서울특별시"));
        assertEquals(location.get(), "서울특별시");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!@#",
            "서울!@# "
    })
    public void 지역은_한글및_숫자_영어조합_1자이상_15자이하로_입력해야함(String location){
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new Location(location);
        });
    }

    @Test
    public void 주소_상세_입력(){
        // when
        AddressDetail addressDetail = new AddressDetail("주소 상세");

        // then
        assertEquals(addressDetail, new AddressDetail("주소 상세"));
        assertEquals(addressDetail.get(), "주소 상세");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!@#",
            "주소 상세#@!@"
    })
    public void 주소_상세는_한글_숫자_영어조합_공백포함_1자이상_20자이하로입력해야함(String detail){
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new AddressDetail(detail);
        });
    }

    @Test
    public void 지역_미입력(){
        // when
        assertThrows(IllegalArgumentException.class, ()->{
            new Location("");
        });
    }

    @Test
    public void 주소_입력(){
        // given
        ChangeAddress changeAddress = ChangeAddress.builder()
                .location("서울특별시")
                .addressDetail("주소 상세")
                .build();

        // when
        Address address = new Address(changeAddress);
        AddressModel addressModel = address.toModel();

        // then
        assertEquals(addressModel.getLocation(), "서울특별시");
        assertEquals(addressModel.getAddressDetail(), "주소 상세");
    }

    @Test
    public void 주소입력시_지역을반드시_입력해야함(){
        // given
        ChangeAddress changeAddress = ChangeAddress.builder().build();

        // when
        assertThrows(IllegalArgumentException.class, ()->{
            new Address(changeAddress);
        });
    }

    @Test
    public void 주소입력시_주소_상세는_입력하지않아도됨(){
        // given
        ChangeAddress changeAddress = ChangeAddress.builder()
                .location("서울특별시")
                .build();

        // when
        Address address = new Address(changeAddress);
        AddressModel addressModel = address.toModel();

        // then
        assertEquals(addressModel.getLocation(), "서울특별시");
        assertNull(addressModel.getAddressDetail());
    }

    CustomerMapper customerMapper = new CustomerMapper();

    @Test
    public void 고객_생성(){
        // given
        RegisterCustomer registerCustomer = RegisterCustomer.builder()
                .category("은행")
                .businessInfo(ChangeBusinessInfo.builder()
                        .name("사업체명")
                        .number("000-00-00000")
                        .build())
                .purchasingManagers(Arrays.asList(ChangePurchasingManager.builder()
                        .name("이름")
                        .email("test@google.com")
                        .jobTitle("직위")
                                .contact(ChangeContact.builder()
                                        .mainTel("000-0000-0000")
                                        .build())
                        .build()))
                .fax("00-000-0000")
                .address(ChangeAddress.builder()
                        .location("지역")
                        .addressDetail("상세주소")
                        .build())
                .build();

        // when
        Customer customer = customerMapper.mapFrom(registerCustomer);
        CustomerModel customerModel = customer.toModel();

        // then
        assertEquals(customerModel.getCategory(), "은행");
        assertEquals(customerModel.getBusinessInfo().getName(),"사업체명");
        assertEquals(customerModel.getBusinessInfo().getNumber(),"000-00-00000");

        assertEquals(customerModel.getAddress().getLocation(),"지역");
        assertEquals(customerModel.getAddress().getAddressDetail(),"상세주소");

        // 고객 생성 유효성 검증을 거쳐야함
        assertNull(customerModel.getSaleState());
    }

    CategoryRepository categoryRepository;
    LocationRepository locationRepository;
    RegisterCustomerValidator registerCustomerValidator;

    @Before
    public void setUp(){
        categoryRepository = mock(CategoryRepository.class);
        locationRepository = mock(LocationRepository.class);
        registerCustomerValidator = new SimpleRegisterCustomerValidator(categoryRepository, locationRepository);

        when(categoryRepository.existByCategory(any()))
                .thenReturn(true);
        when(locationRepository.existByLocation(any()))
                .thenReturn(true);
    }

    @Test
    public void 고객_등록(){
        // given
        RegisterCustomer registerCustomer = aRegisterCustomer().category("카테고리").build();
        Customer customer = customerMapper.mapFrom(registerCustomer);

        // when
        customer.register(registerCustomerValidator);
        CustomerModel customerModel = customer.toModel();

        // then
        assertEquals(customerModel.getSaleState(), SALE);
    }

    @Test
    public void 고객_등록시_카테고리는_제공하는_카테고리만_등록해야함(){
        // given
        RegisterCustomer registerCustomer = aRegisterCustomer().category("카테고리").build();
        Customer customer = customerMapper.mapFrom(registerCustomer);

        when(categoryRepository.existByCategory(new Category("카테고리")))
                .thenReturn(false);

        // when
        assertThrows(CategoryNotFoundException.class, ()->{
            customer.register(registerCustomerValidator);
        });
    }

    @Test
    public void 고객_등록시_지역은_제공하는_지역만_등록해야함(){
        // given
        RegisterCustomer registerCustomer = aRegisterCustomer().category("카테고리").build();
        Customer customer = customerMapper.mapFrom(registerCustomer);

        when(locationRepository.existByLocation(new Location("지역")))
                .thenReturn(false);

        // when
        assertThrows(LocationNotFoundException.class, ()->{
            customer.register(registerCustomerValidator);
        });
    }

    @Test
    public void 업체명_수정() {
        // given
        Customer customer = aCustomer();

        // when
        customer.changeBusinessName(new BusinessName("업체명 수정"));

        // then
        assertEquals(customer.toModel().getBusinessInfo().getName(),"업체명 수정");
    }

    @Test
    public void 상세주소_수정() {
        // given
        Customer customer = aCustomer();

        // when
        customer.changeAddressDetail(new AddressDetail("상세 주소 변경"));

        // then
        assertEquals(customer.toModel().getAddress().getAddressDetail(), "상세 주소 변경");
    }

    @Test
    public void 사업자번호_수정(){
        // given
        Customer customer = aCustomer();

        // when
        customer.changeBusinessNumber(new BusinessNumber("000-00-12345"));

        // then
        assertEquals(customer.toModel().getBusinessInfo().getNumber(), "000-00-12345");
    }

    @Test
    public void 팩스번호_변경(){
        // given
        Customer customer = aCustomer();

        // when
        customer.changeFax(new Tel("123-1234-1234"));

        // then
        assertEquals(customer.toModel().getFax(), "123-1234-1234");
    }

    @Test
    public void 영업중단(){
        // given
        Customer customer = aCustomer();

        // when
        customer.saleStop();

        //then
        assertEquals(customer.toModel().getSaleState(), STOP);
    }

    @Test(expected = AlreadySaleStopException.class)
    public void 이미_영업중단한_고객(){
        // given
        Customer customer = aCustomer();
        customer.saleStop();

        // when
        customer.saleStop();
    }

    @Test(expected = AlreadySaleException.class)
    public void 이미_영업중인_고객(){
        // given
        Customer customer = aCustomer();

        // when
        customer.sale();
    }

    @Test
    public void 담당자_추가(){
        // given
        Customer customer = aCustomer();

        // when
        assertDoesNotThrow(()-> {
            customer.addPurchasingManger(customerMapper.mapFrom(aAddPurchasingManager().build()));
        });
    }

    @Test
    public void 담당고객_등록() {
        // given
        Customer customer = aCustomer();

        // when
        assertDoesNotThrow(()->{
            Responsible.of(customer.getId(), "manager");
        });
    }
}
