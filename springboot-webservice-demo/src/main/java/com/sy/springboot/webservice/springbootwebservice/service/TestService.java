package com.sy.springboot.webservice.springbootwebservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @description: 测试服务
 * @author: OverlookView
 * @create: 2018-09-09 14:34
 **/
@WebService(name = "TestService", // 暴露服务名称
        targetNamespace = "http://service.springbootwebservice.webservice.springboot.sy.com"// 命名空间,一般是接口的包名倒序
)
public interface TestService {
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String sendMessage(@WebParam(name = "username") String username);
}
