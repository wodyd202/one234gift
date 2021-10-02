package com.one234gift.userservice.query.application;

import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.query.application.exception.UserNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional(readOnly = true)
public class QueryUserService implements UserDetailsService {
    private final QueryUserRepository userRepository;

    public QueryUserService(QueryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel findByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException(phone));
        return new User(userModel.getPhone(), userModel.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userModel.getRole())));
    }

}
