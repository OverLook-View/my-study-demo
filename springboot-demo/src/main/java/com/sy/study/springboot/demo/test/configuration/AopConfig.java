package com.sy.study.springboot.demo.test.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-10-30 11:26
 **/
@Configuration
@ComponentScan("com.sy.study.springboot.demo.test")
@EnableAspectJAutoProxy //开启spring对aop支持
public class AopConfig {
}
