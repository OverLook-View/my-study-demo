package com.sy.test.ssm.controller;

import com.sy.test.ssm.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    public String index(Model model, HttpServletRequest request) {
        return "index";
    }
}
