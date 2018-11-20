package com.sy.springboot.demo2.springbootdemo2.controller;

import com.sy.springboot.demo2.springbootdemo2.bean.Demo;
import com.sy.springboot.demo2.springbootdemo2.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-09-11 16:36
 **/
@Controller
@CacheConfig(cacheNames = {"myCache"})
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("")
//    @Cacheable(value = "data", key = "targetClass+methodName")
    public String page(HttpServletRequest request, Model model) {
        List<Demo> list = demoService.queryAll();
        model.addAttribute("list", list);
        System.out.println("controller");
        return "demo";
    }
}
