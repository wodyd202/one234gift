package com.one234gift.employeeService.employee.command.presentation;

import com.one234gift.employeeService.employee.EmployeeAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.employeeService.employee.EmployeeFixture.aEmployee;
import static com.one234gift.employeeService.employee.EmployeeFixture.leavedEmployee;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeStateChangeAPI_Test extends EmployeeAPITest {

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_이미_퇴사한_사원을_재영입_처리_할_수_있음() throws Exception {
        // given
        persistEmployee(leavedEmployee("000-1234-0000"));

        // when
        mockMvc.perform(put("/api/employee/{employeePk}/comeback","000-1234-0000"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_이미_재직중인_사원을_재영입_처리하면_에러발생() throws Exception {
        // given
        persistEmployee(aEmployee("123-1234-1235"));

        // when
        mockMvc.perform(put("/api/employee/{employeePk}/comeback","123-1234-1235"))

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "SALES_EMPLOYEE")
    void 영업사원이_재영입_처리_할_경우_에러발생() throws Exception{
        // when
        mockMvc.perform(put("/api/employee/{employeerPk}/comeback","fobidden"))

        // then
        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_근무중인_사원을_퇴사처리_할_수_있음() throws Exception {
        // given
        persistEmployee(aEmployee("123-1234-1234"));

        // when
        mockMvc.perform(delete("/api/employee/{employeePk}/leave", "123-1234-1234"))

        // then
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "ACCOUNTING_EMPLOYEE")
    void 경리담당자가_이미_퇴사한_사원을_퇴사처리하면_에러발생() throws Exception {
        // given
        persistEmployee(leavedEmployee("123-1234-1210"));

        // when
        mockMvc.perform(delete("/api/employee/{employeePk}/leave", "123-1234-1210"))

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "000-0000-0000", roles = "SALES_EMPLOYEE")
    void 영업담당자가_퇴사_처리시_에러발생() throws Exception{
        // when
        mockMvc.perform(delete("/api/employee/{employeePk}/leave", "fobidden"))

        // then
        .andExpect(status().isForbidden());
    }
}
