package com.sy.springboot.demo2.springbootdemo2.schedule;

import com.sy.springboot.demo2.springbootdemo2.utils.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class WechatCompanyPaymentTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Scheduled(cron = "0 0 9-18/1 * * ?")
    public void task() {
        System.out.println("定时任务执行中 "+DateFormatUtil.toString(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }


}
