package com.sy.test.quartz.test;

import com.sy.test.quartz.schedule.QuartzJobFactory;
import com.sy.test.quartz.schedule.ScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @description:
 * @author: sheny
 * @create: 2018-10-22 14:17
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-application.xml")
public class QuartzDemoTest {
    @Autowired
    private QuartzJobFactory quartzJobFactory;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Test
    public void test1() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Map<String, ScheduleJob> map = QuartzJobFactory.getAllJob();
        for (Map.Entry<String, ScheduleJob> entry : map.entrySet()) {
            ScheduleJob job = entry.getValue();
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        }
    }

}
