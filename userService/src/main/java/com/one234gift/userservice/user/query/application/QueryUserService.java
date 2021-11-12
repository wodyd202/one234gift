package com.one234gift.userservice.query.application;

import com.one234gift.userservice.domain.exception.NotAccessUserException;
import com.one234gift.userservice.domain.exception.UserNotFoundException;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.UserRole;
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
public class QueryUserService implements UserDetailsService {
    private QueryUserRepository userRepository;

    public UserModel findByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(UserNotFoundException::new);
    }

    /**
     * @param target
     * # 사원 단건 조회
     */
    public UserModel getUserModel(String target) throws NotAccessUserException {
        UserModel targetInfo = userRepository.findByPhone(target).orElseThrow(UserNotFoundException::new);

        // 요청자 정보 가져옴
        UserModel requesterInfo = getRequester();

        // 다른 사원의 정보를 조회할 수 있는가 권한 체크
        verifyisReadAbleTargetInfo(targetInfo, requesterInfo);
        return targetInfo;
    }

    /**
     * # 요청자 정보 생성
     */
    private UserModel getRequester(){
        return UserModel.builder()
                .phone(getRequesterPhone())
                .role(UserRole.valueOf(getRequesterRoles().get(0)))
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

    private void verifyisReadAbleTargetInfo(UserModel targetInfo, UserModel requesterInfo) throws NotAccessUserException {
        if(!targetInfo.isReadAble(requesterInfo)){
            throw new NotAccessUserException("다른 사원의 정보를 조회할 수 없습니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException(phone));
        if(userModel.isLeaved()){
            throw new UsernameNotFoundException(phone);
        }
        return new User(userModel.getPhone(), userModel.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userModel.getRole())));
    }
}
