package com.example.mpd.mybatisplusdemo.service.impl;

import com.example.mpd.mybatisplusdemo.entity.User;
import com.example.mpd.mybatisplusdemo.mapper.UserMapper;
import com.example.mpd.mybatisplusdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2021-10-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
