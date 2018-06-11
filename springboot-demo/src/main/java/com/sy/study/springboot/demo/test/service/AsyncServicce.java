package com.sy.study.springboot.demo.test.service;

import com.sy.study.springboot.demo.test.bean.Demo;
import com.sy.study.springboot.demo.test.mapper.DemoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncServicce {
    private final Logger logger= LoggerFactory.getLogger(AsyncServicce.class);

    @Autowired
    private DemoMapper demoMapper;

    @Async
    public List<Demo> findUser(String user) throws InterruptedException {
        List<Demo> list = demoMapper.select(user);
        Thread.sleep(10000l);
        return list;
    }
}
