package com.sy.springboot.webservice.springbootwebservice.service.impl;

import com.sy.springboot.webservice.springbootwebservice.service.TestService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @description: 测试服务实现
 * @author: OverlookView
 * @create: 2018-09-09 14:37
 **/
@WebService(serviceName = "TestService", // 与接口中指定的name一致
        targetNamespace = "http://service.springbootwebservice.webservice.springboot.sy.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.sy.springboot.webservice.springbootwebservice.service.TestService"// 接口地址
)
@Component
public class TestServiceImpl implements TestService {
    @Override
    public String sendMessage(String username) {
        return "hello " + username;
    }
}
