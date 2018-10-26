package com.sy.springboot.demo2.springbootdemo2.service.impl;

import com.sy.springboot.demo2.springbootdemo2.bean.Demo;
import com.sy.springboot.demo2.springbootdemo2.mapper.DemoMapper;
import com.sy.springboot.demo2.springbootdemo2.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-09-11 16:34
 **/
@Service
@Transactional
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

}
