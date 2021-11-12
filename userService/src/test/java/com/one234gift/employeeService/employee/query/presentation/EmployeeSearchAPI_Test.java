package com.one234gift.employeeService.employee.query.presentation;

import com.one234gift.employeeService.employee.EmployeeAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.employeeService.employee.EmployeeFixture.aEmployee;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 사원 조회 API 테스트
 */
public class EmployeeSearchAPI_Test extends EmployeeAPITest {

    @Test
    @WithMockUser(username = "123-1234-1237", roles = "SALES_EMPLOYEE")
    void 다른_사원의_정보를_조회할_수_없음() throws Exception{
        // given
        persistEmployee(aEmployee("010-0000-0000"));

        // when
        mockMvc.perform(get("/api/employee/{phone}","010-0000-0000"))

        // then
        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "123-1234-1236", roles = "SALES_EMPLOYEE")
    void 사원_정보_조회() throws Exception {
        // given
        persistEmployee(aEmployee("123-1234-1236"));

        // when
        mockMvc.perform(get("/api/employee/{phone}","123-1234-1236"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "123-1234-1235", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자는_다른_사원의_정보를_조회할_수_있음() throws Exception {
        // given
        persistEmployee(aEmployee("010-0000-0000"));

        // when
        mockMvc.perform(get("/api/employee/{phone}","010-0000-0000"))

        // then
        .andExpect(status().isOk());
    }
}
