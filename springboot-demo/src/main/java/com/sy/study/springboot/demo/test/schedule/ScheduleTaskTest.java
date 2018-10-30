package com.sy.study.springboot.demo.test.schedule;

import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskTest {

    //@Scheduled(fixedRate = 5000)    //上次开始执行5秒后执行
    //@Scheduled(fixedDelay = 5000)   //上次执行完5秒后执行
    //@Scheduled(initialDelay = 1000,fixedRate = 5000)    //第一次延迟1秒执行，之后5秒后执行
//    @Scheduled(cron = "0/5 * * * * ?")    //系统时间从0开始5秒执行一次
    public void myTask(){
        System.out.println("定时任务报时");
    }
}
