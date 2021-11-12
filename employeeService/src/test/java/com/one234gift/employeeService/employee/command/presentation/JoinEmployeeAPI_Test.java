package com.one234gift.employeeService.employee.command.presentation;

import com.one234gift.employeeService.employee.EmployeeAPITest;
import com.one234gift.employeeService.employee.command.application.model.JoinEmployee;
import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.one234gift.employeeService.employee.EmployeeFixture.aEmployee;
import static com.one234gift.employeeService.employee.EmployeeFixture.aRegisterEmployee;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 사원 등록 API 테스트
 */
public class JoinEmployeeAPI_Test extends EmployeeAPITest {

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자는_사원을_입사시킬_수_있음() throws Exception{
        // given
        JoinEmployee joinEmployee = aRegisterEmployee().position(EmployeePosition.ACCOUNTING_EMPLOYEE).build();

        // when
        assertJoinEmployee(joinEmployee,

        status().isOk());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_사원_입사_처리시_중복된_휴대폰_번호가_존재하면_에러발생() throws Exception {
        // given
        persistEmployee(aEmployee("134-1234-1234"));
        JoinEmployee joinEmployee = aRegisterEmployee().phone("134-1234-1234").build();

        // when
        assertJoinEmployee(joinEmployee,

        // then
        status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "SALES_EMPLOYEE")
    void 영업담당자는_다른_사원을_입사_처리_할_수_없음() throws Exception {
        // given
        JoinEmployee joinEmployee = aRegisterEmployee().phone("134-1234-1234").build();

        // when
        assertJoinEmployee(joinEmployee,

        // then
        status().isForbidden());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_휴대폰번호을_양식에_맞지_않게_입력하면_에러발생() throws Exception {
        JoinEmployee joinEmployee = aRegisterEmployee()
                .phone("invalid")
                .build();

        assertJoinEmployee(joinEmployee, status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_휴대폰번호를_입력하지_않으면_에러발생() throws Exception {
        JoinEmployee joinEmployee = aRegisterEmployee()
                .phone(null)
                .build();

        assertJoinEmployee(joinEmployee, status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_이름을_양식에_맞지_않게_입력하면_에러발생() throws Exception {
        JoinEmployee joinEmployee = aRegisterEmployee()
                .name("invalid")
                .build();

        assertJoinEmployee(joinEmployee, status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_이름을_입력하지_않으면_에러발생() throws Exception {
        JoinEmployee joinEmployee = aRegisterEmployee()
                .name(null)
                .build();

        assertJoinEmployee(joinEmployee, status().isBadRequest());
    }

    void assertJoinEmployee(JoinEmployee joinEmployee, ResultMatcher resultMatcher) throws Exception{
        mockMvc.perform(post("/api/employee")
                        .content(objectMapper.writeValueAsString(joinEmployee))
                        .contentType(APPLICATION_JSON))
                .andExpect(resultMatcher);
    }

}
