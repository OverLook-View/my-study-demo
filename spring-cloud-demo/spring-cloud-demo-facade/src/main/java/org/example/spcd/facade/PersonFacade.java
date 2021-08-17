package org.example.spcd.facade;

import feign.Param;
import org.example.spcd.bean.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "spring-cloud-demo-service")
public interface PersonFacade {

    @RequestMapping("/person/age")
    String getPersonAge(@Param("person") Person person);
}
