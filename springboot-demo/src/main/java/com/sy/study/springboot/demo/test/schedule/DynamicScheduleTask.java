package com.sy.study.springboot.demo.test.schedule;

import com.sy.study.springboot.demo.test.configuration.MyScheduleJob;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-11-01 09:04
 **/
@Configuration
@EnableScheduling
//@Component
public class DynamicScheduleTask {
    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    private int i=0;

    @Scheduled(fixedRate = 60000)
    public void scheduleUpdateCronTrigger() throws SchedulerException {
//        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
//        String cronExpression = trigger.getCronExpression();
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
//        trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey()).withSchedule(cronScheduleBuilder).build();
//        scheduler.rescheduleJob(cronTrigger.getKey(),trigger);

        MyScheduleJob myScheduleJob = new MyScheduleJob();
        myScheduleJob.setJobId("10001" + i);
        myScheduleJob.setJobName("任务" + i++);
        myScheduleJob.setJobGroup("group");
        myScheduleJob.setJobStatus("1");
        myScheduleJob.setCronExpression("0/5 * * * * ?");
        myScheduleJob.setDesc("数据导入任务");
        TriggerKey triggerKey = TriggerKey.triggerKey(myScheduleJob.getJobName(), myScheduleJob.getJobGroup());
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder.newJob(MyScheduleJob.class).withIdentity(myScheduleJob.getJobName(), myScheduleJob.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", myScheduleJob);
            //表达式调度构建起
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(myScheduleJob.getCronExpression());
            //按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(myScheduleJob.getJobName(), myScheduleJob.getJobGroup()).withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            //trigger已存在，更新定时设置
            //表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(myScheduleJob.getCronExpression());
            //按新的cronExpression表达式构建trigger
            CronTrigger cronTrigger= (CronTrigger) trigger;
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            //按新的trigger设置job执行
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        }
    }
}
