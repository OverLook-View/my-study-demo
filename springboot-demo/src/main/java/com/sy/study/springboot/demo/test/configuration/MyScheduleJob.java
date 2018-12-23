package com.sy.study.springboot.demo.test.configuration;

import lombok.Data;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-11-01 11:14
 **/
@DisallowConcurrentExecution
@Data
public class MyScheduleJob implements Job {
    private String jobId;
    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String cronExpression;
    private String desc;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("任务运行：" + new Date());
        MyScheduleJob scheduleJob = (MyScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        System.out.println("任务名称 = 【" + scheduleJob.getJobName() + "】");
    }
}
