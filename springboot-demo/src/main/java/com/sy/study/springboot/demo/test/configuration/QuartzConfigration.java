package com.sy.study.springboot.demo.test.configuration;

import com.sy.study.springboot.demo.test.schedule.ScheduleTaskTest;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-10-31 09:42
 **/
@Configuration
public class QuartzConfigration {

    /**
     * attention:
     * Details：配置定时任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTaskTest scheduleTaskTest){
        MethodInvokingJobDetailFactoryBean jobDetail=new MethodInvokingJobDetailFactoryBean();
        //是否并发执行
        jobDetail.setConcurrent(false);
        jobDetail.setName("schedule task");//设置任务名字
        jobDetail.setGroup("group");//设置分组
        jobDetail.setTargetObject(scheduleTaskTest);//设置执行实体对象
        jobDetail.setTargetMethod("myTask");

        return jobDetail;
    }

    /**
     * attention:
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail){
        CronTriggerFactoryBean trigger=new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setCronExpression("0 0 * * * ?");
        trigger.setName("trigger");
        return trigger;
    }

    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean scheduleFactory(Trigger cronTrigger){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(cronTrigger);
        return bean;
    }
}
