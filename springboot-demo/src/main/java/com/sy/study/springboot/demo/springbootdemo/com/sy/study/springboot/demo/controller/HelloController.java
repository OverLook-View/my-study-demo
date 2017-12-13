package com.sy.study.springboot.demo.springbootdemo.com.sy.study.springboot.demo.controller;

import com.sy.study.springboot.demo.springbootdemo.com.sy.study.springboot.demo.bean.ConfigBean;
import com.sy.study.springboot.demo.springbootdemo.com.sy.study.springboot.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class, User.class})
public class HelloController {
    @Autowired
    private ConfigBean configBean;
    @Autowired
    private User user;
    @RequestMapping("/")
    public String hello() {

        return configBean.getGreeting()+" >>>>"+configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();
    }

    @RequestMapping("/myInfo")
    public String myInfo(){
        return user.getName()+">>>>"+user.getAge();
    }
}
