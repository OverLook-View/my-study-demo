package com.sy.security.securitydemo2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-09-16 22:11
 **/
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    // 原请求信息的缓存及恢复
    private RequestCache requestCache = new HttpSessionRequestCache();
    // 用于重定向
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping("authenication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Map requireAuthenication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("引发挑战的请求是：" + redirectUrl);
            if (redirectUrl.endsWith(".html")) {
                redirectStrategy.sendRedirect(request, response, "/login.html");
            }
        }
        HashMap map = new HashMap();
        map.put("msg", "访问的服务需要身份认证，请引导用户到登录页");
        return new HashMap();
    }
}
