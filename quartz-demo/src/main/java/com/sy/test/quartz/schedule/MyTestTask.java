package com.sy.test.quartz.schedule;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-10-18 15:12
 **/
@Component
public class MyTestTask {

    public void testTask(){
        System.out.println("测试任务执行了-------------- "+new Date());
    }
}
