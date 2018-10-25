package com.sy.test.quartz.test;

import com.sy.test.quartz.schedule.MyQuartzJobFactory;
import com.sy.test.quartz.schedule.ScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
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
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void scheduleTest() throws SchedulerException, InterruptedException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Map<String, ScheduleJob> map = MyQuartzJobFactory.getAllJob();
        for (Map.Entry<String, ScheduleJob> entry : map.entrySet()) {
            ScheduleJob job = entry.getValue();
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                //表达式调度构建起
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(cronScheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
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
        Thread.sleep(60l * 1000);
    }

    public void queryTest() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<>(currentlyExecutingJobs.size());
        for (JobExecutionContext executingJob : currentlyExecutingJobs) {
            ScheduleJob scheduleJob = new ScheduleJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            scheduleJob.setJobName(jobKey.getName());
            scheduleJob.setJobGroup(jobKey.getGroup());
            scheduleJob.setDesc("触发器：" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            scheduleJob.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                scheduleJob.setCronExpression(cronExpression);
            }
            jobList.add(scheduleJob);
        }
    }

    @Test
    public void pauseTest() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        ScheduleJob scheduleJob = new ScheduleJob();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    @Test
    public void resumeTest() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        ScheduleJob scheduleJob = new ScheduleJob();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    @Test
    public void deleteTest() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        ScheduleJob scheduleJob = new ScheduleJob();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);
    }
}
