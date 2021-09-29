package com.one234gift.customerservice.domain;

/**
 * 고객(업체)
 */
public class Customer {

    /**
     * 고객(업체) 고유 번호
     */
    private Long id;

    /**
     * 고객(업체) 분류(은행, 우체국 등)[필수]
     * # 서버에서 제공하는 분류만 허용함
     */
    private final Category category;

    /**
     * 고객(업체) 정보
     * - 업체명[필수]
     * - 사업자번호
     */
    private BusinessInfo businessInfo;

    /**
     * 고객(업체) 담당자 정보
     * - 이름[필수]
     * - 이메일
     * - 직위
     */
    private PurchasingManager purchasingManager;

    /**
     * 고객(업체) 연락처
     * - 대표 전화번호[필수]
     * - 기타 전화번호
     * - 팩스
     */
    private Contact contact;

    /**
     * 고객(업체) 주소
     * - 지역(서울특별시, 경기도 등)[필수]
     * # 서버에서 제공하는 지역만 허용함
     * - 상세 주소
     */
    private Address address;

    /**
     * 비고
     */
    private Note note;

    /**
     * 고객 등록 승인 상태
     * - NO(미승인)
     * - YES(승인)
     * # 영업사원이 등록 후 경리사원에게 허가를 받으면 YES 상태가 됨
     */
    private AllowState allowState;

    /**
     * 고객 판매 상태
     * - SALE(판매중)
     * - STOP(판매중지)
     */
    private SaleState saleState;

    private Customer(RegisterCustomer registerCustomer) {
        categoryValidation(registerCustomer.getCategory());
        category = new Category(registerCustomer.getCategory());

        businessInfoValidation(registerCustomer.getBusinessInfo());
        businessInfo = new BusinessInfo(registerCustomer.getBusinessInfo());

        purchasingManagerValidation(registerCustomer.getPurchasingManager());
        purchasingManager = new PurchasingManager(registerCustomer.getPurchasingManager());

        contactValidation(registerCustomer.getContact());
        contact = new Contact(registerCustomer.getContact());

        addressValidation(registerCustomer.getAddress());
        address = new Address(registerCustomer.getAddress());

        if(registerCustomer.getNote() == null){
            note = new Note();
        }else{
            note = new Note(registerCustomer.getNote());
        }
    }



    private void businessInfoValidation(ChangeBusinessInfo businessInfo) {
        if(businessInfo == null){
            throw new IllegalArgumentException("고객 정보를 입력해주세요.");
        }
    }

    private void purchasingManagerValidation(ChangePurchasingManager purchasingManager) {
        if(purchasingManager == null){
            throw new IllegalArgumentException("고객 담당자를 입력해주세요.");
        }
    }

    private void contactValidation(ChangeContact contact) {
        if(contact == null){
            throw new IllegalArgumentException("고객 연락처를 입력해주세요.");
        }
    }

    private void categoryValidation(String category) {
        if(category == null){
            throw new IllegalArgumentException("분류를 입력해주세요.");
        }
    }

    private void addressValidation(ChangeAddress address) {
        if(address == null){
            throw new IllegalArgumentException("고객 주소지 정보를 입력해주세요.");
        }
    }



    public static Customer registerWith(RegisterCustomer registerCustomer) {
        return new Customer(registerCustomer);
    }

    public CustomerModel toModel() {
        return CustomerModel.builder()
                .category(category.get())
                .businessInfo(businessInfo.toModel())
                .purchasingManager(purchasingManager.toModel())
                .contact(contact.toModel())
                .address(address.toModel())
                .note(note.get())
                .allowState(allowState)
                .saleState(saleState)
                .build();
    }
}
