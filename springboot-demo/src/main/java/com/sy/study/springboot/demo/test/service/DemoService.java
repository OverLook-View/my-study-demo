package com.sy.study.springboot.demo.test.service;

import com.sy.study.springboot.demo.test.bean.Demo;
import com.sy.study.springboot.demo.test.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    public List<Demo> select(String name){
        return demoMapper.select(name);
    }
}
