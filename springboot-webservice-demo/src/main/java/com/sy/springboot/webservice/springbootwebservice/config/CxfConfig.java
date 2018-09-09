package com.sy.springboot.webservice.springbootwebservice.config;

import com.sy.springboot.webservice.springbootwebservice.service.TestService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @description: cxf配置
 * @author: OverlookView
 * @create: 2018-09-09 14:40
 **/
@Configuration
public class CxfConfig {
    @Autowired
    private TestService testService;
    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus,testService);
        endpoint.publish("/TestService");
        return endpoint;
    }
}
