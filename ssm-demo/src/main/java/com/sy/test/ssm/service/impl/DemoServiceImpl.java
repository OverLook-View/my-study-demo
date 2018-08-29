package com.sy.test.ssm.service.impl;

import com.sy.test.ssm.mapper.DemoMapper;
import com.sy.test.ssm.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;
}
