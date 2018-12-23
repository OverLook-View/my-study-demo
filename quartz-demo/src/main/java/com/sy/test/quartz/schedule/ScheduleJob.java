package com.sy.test.quartz.schedule;

import lombok.Data;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-10-22 11:17
 **/
@DisallowConcurrentExecution
@Data
public class ScheduleJob implements Job {

    private String jobId;
    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String cronExpression;
    private String desc;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("任务运行：" + new Date());
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        System.out.println("任务名称 = 【" + scheduleJob.getJobName() + "】");
    }
}
