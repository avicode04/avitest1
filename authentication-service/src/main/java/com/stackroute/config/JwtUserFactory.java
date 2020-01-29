package com.stackroute.config;

import com.stackroute.domain.AuthUser;
import com.stackroute.domain.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class JwtUserFactory {
    public static JwtUser create(AuthUser user) {
        return new JwtUser(user.getId(),user.getEmailId(),user.getPassword(), user.getRole(), user, maptoGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE_"+user.getRole()))));
    }

    private static List<GrantedAuthority> maptoGrantedAuthorities(ArrayList<String> authorities) {
        return authorities.stream().map(Authority -> new SimpleGrantedAuthority(Authority)).collect(Collectors.toList());
    }
}
