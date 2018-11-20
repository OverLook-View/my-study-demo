package com.sy.springboot.demo2.springbootdemo2.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-09-14 09:10
 **/
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                //  定义当需要用户登录时候，转到的登录页面。
                .and()
                .authorizeRequests()    // 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest()           // 任何请求,登录后可以访问
                .authenticated();
    }
}
