package com.sy.test.quartz.schedule;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: sheny
 * @create: 2018-10-22 10:02
 **/
public class MyQuartzJobFactory {
    /** 计划任务map */
    private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();

    static {
        for (int i = 0; i < 5; i++) {
            ScheduleJob job = new ScheduleJob();
            job.setJobId("10001" + i);
            job.setJobName("data_import" + i);
            job.setJobGroup("dataWork");
            job.setJobStatus("1");
            job.setCronExpression("0/5 * * * * ?");
            job.setDesc("数据导入任务");
            addJob(job);
        }
    }

    public static void addJob(ScheduleJob scheduleJob) {
        jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
    }


    public static Map<String, ScheduleJob> getAllJob(){
        return jobMap;
    }
}
