package com.example.mpd.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpd.mybatisplusdemo.entity.User;
import com.example.mpd.mybatisplusdemo.mapper.UserMapper;
import com.example.mpd.mybatisplusdemo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        IPage<User> page=new Page<>();
        page.setPages(1);
        page.setSize(10);
        Wrapper<User> queryWrapper=new QueryWrapper<>();
        IPage<User> page1 = userMapper.selectPage(page, queryWrapper);
        page1.getRecords().forEach(System.out::println);

        User user=new User();
        userService.save(user);
    }

}
