package com.sy.study.springboot.demo.test.controller;

import com.sy.study.springboot.demo.test.aspect.Log;
import com.sy.study.springboot.demo.test.configuration.ConfigBean;
import com.sy.study.springboot.demo.test.configuration.User;
import com.sy.study.springboot.demo.test.service.AaaService;
import com.sy.study.springboot.demo.test.service.DemoService;
import com.sy.study.springboot.demo.test.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@EnableConfigurationProperties({ConfigBean.class, User.class})
public class HelloController {
    @Autowired
    private ConfigBean configBean;
    @Autowired
    private User user;
    @Autowired
    private DemoService demoService;
    @Autowired
    private RedisService redisService;
    @Autowired
    @Qualifier("aaaService")
    private AaaService aaaService;
    @RequestMapping("/")
    @Log(module = "hello",remark = "测试",operate = Log.OperateEnum.query)
    public String hello() {

        return configBean.getGreeting()+" >>>>"+configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();
    }

    @RequestMapping("/myInfo")
    public String myInfo(){
        return user.getName()+">>>>"+user.getAge();
    }

    @RequestMapping("/demo")
    public String demo(){
        return demoService.select("").toString();
    }

    @RequestMapping("/redis")
    public String redis(){
        redisService.setRedis("key","value",300);
        Set<String> set = redisService.getAllRedis();
        return set.toString();
    }
}
