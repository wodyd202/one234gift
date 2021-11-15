package com.one234gift.employeeService.employee.query.application;

import com.one234gift.employeeService.employee.domain.exception.NotAccessEmployeeException;
import com.one234gift.employeeService.employee.domain.exception.EmployeeNotFoundException;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class QueryEmployeeService implements UserDetailsService {
    private QueryEmployeeRepository employeeRepository;

    /**
     * @param target
     * # 사원 단건 조회
     */
    public EmployeeModel getEmployeeModel(String target) throws NotAccessEmployeeException {
        EmployeeModel targetInfo = employeeRepository.findByPhone(target).orElseThrow(EmployeeNotFoundException::new);

        // 요청자 정보 가져옴
        EmployeeModel requesterInfo = getRequester();

        // 다른 사원의 정보를 조회할 수 있는가 권한 체크
        verifyisReadAbleTargetInfo(targetInfo, requesterInfo);
        return targetInfo;
    }

    /**
     * # 요청자 정보 생성
     */
    private EmployeeModel getRequester(){
        return EmployeeModel.builder()
                .phone(getRequesterPhone())
                .role(EmployeePosition.valueOf(getRequesterRoles().get(0)))
                .build();
    }

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getRequesterPhone(){
        return getAuthentication().getName();
    }

    private List<String> getRequesterRoles() {
        return getAuthentication().getAuthorities().stream()
                .map(r -> r.getAuthority().replace("ROLE_","")).collect(Collectors.toList());
    }

    private void verifyisReadAbleTargetInfo(EmployeeModel targetInfo, EmployeeModel requesterInfo) throws NotAccessEmployeeException {
        if(!targetInfo.isReadAble(requesterInfo)){
            throw new NotAccessEmployeeException("다른 사원의 정보를 조회할 수 없습니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        EmployeeModel employeeModel = employeeRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException(phone));
        if(employeeModel.isLeaved()){
            throw new UsernameNotFoundException(phone);
        }
        return new User(employeeModel.getPhone(), employeeModel.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_" + employeeModel.getPosition())));
    }
}
