package com.example.sbad.service.impl;

import com.example.sbad.service.HolidayService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class HolidayServiceImpl implements HolidayService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public String start(String userId) {
        String instanceKey = "holiday_processes";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        logger.info("启动流程用户:{}", userId);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(instanceKey, map);
        logger.info("启动流程实例成功:{}", instance);
        logger.info("流程实例ID:{}", instance.getId());
        logger.info("流程定义ID:{}", instance.getProcessDefinitionId());
        return instance.getId();
    }

    @Override
    public Boolean employeeApply(String instanceId, Integer leaveDays, String leaveReason) {
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        if (task == null) {
            logger.info("任务ID:{}查询到任务为空", instanceId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("days", leaveDays);
        map.put("data", new Date());
        map.put("reason", leaveReason);
        taskService.complete(task.getId(), map);
        logger.info("执行【员工申请】环节，流程推进到【上级审核】环节");
        return true;
    }

    @Override
    public Map<String,Object> showTaskVariable(String instanceId) {
        org.activiti.engine.task.Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        Integer days = (Integer) taskService.getVariable(task.getId(), "days");
        Date data = (Date) taskService.getVariable(task.getId(), "data");
        String reason = (String) taskService.getVariable(task.getId(), "reason");
        String userId = (String) taskService.getVariable(task.getId(), "userId");
        HashMap<String, Object> map = new HashMap<>();
        map.put("请假天数",days);
        map.put("请假日期",data);
        map.put("请假原因",reason);
        map.put("请加人id",userId);
        return map;
    }

    @Override
    public Boolean departmentAudit(String instanceId, String departmentOpinion) {
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        Map<String, Object> map = new HashMap<>();
        map.put("departmentOpinion", departmentOpinion);
        taskService.complete(task.getId(), map);
        logger.info("添加审批意见：{}，请假流程结束",departmentOpinion);
        return true;
    }
}
