package com.sy.test.quartz.schedule;

import lombok.Data;

/**
 * @description:
 * @author: sheny
 * @create: 2018-10-22 11:17
 **/
@Data
public class ScheduleJob {

    private String jobId;
    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String cronExpression;
    private String desc;

}
