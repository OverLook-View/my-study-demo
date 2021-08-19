package com.example.sbad.service;

import org.activiti.engine.task.Task;

import java.util.Map;

public interface HolidayService {

    String start(String userId);
    Boolean employeeApply(String instanceId,Integer leaveDays,String leaveReason);
    Map<String,Object> showTaskVariable(String instanceId);
    Boolean departmentAudit(String instanceId,String departmentOpinion);

}
