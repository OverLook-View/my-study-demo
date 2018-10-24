package com.sy.test.quartz.test;

import com.sy.test.quartz.schedule.MyQuartzJob;
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
    private MyQuartzJob myQuartzJob;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void test1() throws SchedulerException, InterruptedException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Map<String, ScheduleJob> map = MyQuartzJob.getAllJob();
        for (Map.Entry<String, ScheduleJob> entry : map.entrySet()) {
            ScheduleJob job = entry.getValue();
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                //表达式调度构建起
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(cronScheduleBuilder).build();
                scheduler.scheduleJob(jobDetail,trigger);
            } else {
                //trigger已存在，更新定时设置
                //表达式调度构建器
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                //按新的cronExpression表达式构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
                //按新的trigger设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        Thread.sleep(60l*1000);
    }

}
