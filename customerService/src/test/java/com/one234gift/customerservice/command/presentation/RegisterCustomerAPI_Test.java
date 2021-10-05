package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.APITest;
import com.one234gift.customerservice.domain.model.ChangeBusinessInfo;
import com.one234gift.customerservice.domain.model.ChangeContact;
import com.one234gift.customerservice.domain.model.ChangePurchasingManager;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000", roles = {"SalesUser"})
public class RegisterCustomerAPI_Test extends APITest {

    @Test
    void 고객_등록() throws Exception{
        RegisterCustomer registerCustomer = aRegisterCustomer().build();
        assertRegisterCustomer(registerCustomer, status().isOk());
    }

    @Test
    void 카테고리_미입력() throws Exception{
        RegisterCustomer registerCustomer = aRegisterCustomer().category(null).build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 카테고리가_빈값() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer().category("").build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 카테고리가_유효하지_않음() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer().category("invalid").build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 고객정보_미입력() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer().businessInfo(null).build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 고객정보중_업체명_미입력() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer().businessInfo(ChangeBusinessInfo.builder()
                        .name(null)
                .build()).build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 고객정보중_업체명이_빈값() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer().businessInfo(ChangeBusinessInfo.builder()
                .name("")
                .build()).build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 고객정보중_업체명이_유효하지_않음() throws Exception{
        RegisterCustomer registerCustomer = aRegisterCustomer().businessInfo(ChangeBusinessInfo.builder()
                .name("invalid")
                .build()).build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 고객정보중_사업자번호는_입력하지_않아도됨() throws Exception{
        RegisterCustomer registerCustomer = aRegisterCustomer().businessInfo(ChangeBusinessInfo.builder()
                .name("업체명")
                .number(null)
                .build()).build();
        assertRegisterCustomer(registerCustomer, status().isOk());
    }

    @Test
    void 고객정보중_사업자번호가_필수는_아니지만_입력했을경우_양식을_지켜야함() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer().businessInfo(ChangeBusinessInfo.builder()
                .name("업체명")
                .number("")
                .build()).build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 구매담당자_이름이_빈값() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer()
                .purchasingManagers(Arrays.asList(ChangePurchasingManager.builder()
                                .name("")
                                .contact(ChangeContact.builder()
                                        .mainTel("000-0000-0000")
                                        .build())
                        .build()))
                .build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 구매담당자_이름이_유효하지_않음() throws Exception{
        RegisterCustomer registerCustomer = aRegisterCustomer()
                .purchasingManagers(Arrays.asList(ChangePurchasingManager.builder()
                        .name("invalid")
                        .contact(ChangeContact.builder()
                                .mainTel("000-0000-0000")
                                .build())
                        .build()))
                .build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 구매담당자_이메일이_필수항목은_아니지만_입력했을_경우_양식을_지켜야함() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer()
                .purchasingManagers(Arrays.asList(ChangePurchasingManager.builder()
                        .name("구매담당자명")
                        .email("invalid")
                        .contact(ChangeContact.builder()
                                .mainTel("000-0000-0000")
                                .build())
                        .build()))
                .build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 구매담당자_직위가_필수는_아니지만_입력했을_경우_유효한_직위정보를_입력해야함() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer()
                .purchasingManagers(Arrays.asList(ChangePurchasingManager.builder()
                        .name("구매담당자명")
                        .jobTitle("invalid")
                                .contact(ChangeContact.builder()
                                        .mainTel("000-0000-0000")
                                        .build())
                        .build()))
                .build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 구매담당자_대표연락처는_필수사항임() throws Exception{
        RegisterCustomer registerCustomer = aRegisterCustomer()
                .purchasingManagers(Arrays.asList(ChangePurchasingManager.builder()
                        .name("구매담당자명")
                        .contact(ChangeContact.builder()
                                .build())
                        .build()))
                .build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    @Test
    void 구매담당자는_최소_한명이상_입력해야함() throws Exception {
        RegisterCustomer registerCustomer = aRegisterCustomer()
                .purchasingManagers(Arrays.asList()).build();
        assertRegisterCustomer(registerCustomer, status().isBadRequest());
    }

    private void assertRegisterCustomer(RegisterCustomer registerCustomer, ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(registerCustomer)))
                .andExpect(resultMatcher);
    }
}