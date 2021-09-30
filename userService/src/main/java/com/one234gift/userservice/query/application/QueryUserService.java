package com.one234gift.userservice.query.application;

import com.one234gift.userservice.domain.model.UserModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class QueryUserService implements UserDetailsService {
    private final QueryUserRepository userRepository;

    public QueryUserService(QueryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException(phone));
        return new User(userModel.getPhone(), userModel.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userModel.getRole())));
    }
}
