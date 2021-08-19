package com.example.sbad;

import com.example.sbad.service.HolidayService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class SpringbootActivitiDemoApplicationTests {

    @Autowired
    private HolidayService holidayService;

    @Test
    void holidayTest() {
        String instanceId = holidayService.start("xiaoming");
        Boolean applyB = holidayService.employeeApply(instanceId, 10, "回老家结婚");
        Map<String, Object> map = holidayService.showTaskVariable(instanceId);
        System.out.println(map.toString());
        Boolean departmentAuditB = holidayService.departmentAudit(instanceId, "早去早回");
    }

}
