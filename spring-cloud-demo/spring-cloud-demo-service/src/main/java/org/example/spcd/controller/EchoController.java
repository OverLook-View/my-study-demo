package org.example.spcd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EchoController {

    @RequestMapping("/echo/{string}")
    @ResponseBody
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }
}
