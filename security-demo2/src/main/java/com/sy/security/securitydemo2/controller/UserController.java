package com.sy.security.securitydemo2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-09-14 15:37
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping
    public String getUsers() {
        return "hello spring security";
    }
}
