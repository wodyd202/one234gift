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

}
