package com.one234gift.userservice.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenCheckEndpoint {

    @GetMapping("user")
    public Map<String,Object> user(OAuth2Authentication oAuth2Authentication){
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", oAuth2Authentication.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(oAuth2Authentication.getUserAuthentication().getAuthorities()));
        return userInfo;
    }
}
