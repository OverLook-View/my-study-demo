package com.sy.study.springboot.demo.springbootdemo.com.sy.study.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String hello() {
        return "Greetings form Spring Boot!";
    }
}
