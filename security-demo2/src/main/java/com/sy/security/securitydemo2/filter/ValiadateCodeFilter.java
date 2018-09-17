package com.sy.security.securitydemo2.filter;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 验证码过滤器
 * @author: OverlookView
 * @create: 2018-09-17 22:48
 **/
public class ValiadateCodeFilter extends OncePerRequestFilter {

    //private SessionStrategy sessionStrategy=new HttpSessionStrategy();
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if ("/user/login".equals(httpServletRequest.getRequestURI())&&"post".equalsIgnoreCase(httpServletRequest.getMethod())){
            try{
                //valiadate(new ServletWebRequest(httpServletRequest));
            }catch (Exception e){

            }
        }
    }
}
