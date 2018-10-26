package com.sy.springboot.demo2.springbootdemo2.controller;

import com.alibaba.fastjson.JSON;
import com.sy.springboot.demo2.springbootdemo2.dto.CommonResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 异常公共处理
 * @author: OverlookView
 * @create: 2018-09-12 13:40
 **/
@ControllerAdvice
public class ExceptionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error("系统异常：" + ex);
        ex.printStackTrace();
        String url = request.getContextPath();
        if(!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/text;charset=UTF-8");
            try {
                CommonResponseDto<String> dto = new CommonResponseDto<>("500","系统异常",ex.toString());
                dto.setState("1");
                String jsonString = JSON.toJSONString(dto);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(jsonString);
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("系统返回ajax请求异常失败：" + ex);
            }
            return null;
        }

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", "500");
        model.addObject("error", "非常抱歉，系统出错了，请稍后再试");
        model.addObject("message", ex.toString());
        return model;
    }

}
