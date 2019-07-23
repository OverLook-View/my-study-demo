package com.sy.study.springboot.demo.test.service;

import com.sy.study.springboot.demo.test.bean.Demo;
import com.sy.study.springboot.demo.test.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = {"myCache"})
@Service
@Transactional
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Cacheable(value = "demo", key = "targetClass+methodName+#p0")
    public List<Demo> select(String name) {
        return demoMapper.select(name);
    }
}
