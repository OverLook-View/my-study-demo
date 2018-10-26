package com.sy.springboot.demo2.springbootdemo2.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-09-14 14:26
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User(s, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
