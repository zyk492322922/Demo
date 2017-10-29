package com.zyk.demo.service.user;

import com.zyk.demo.common.RedisUtil;
import com.zyk.demo.dao.UserMapper;
import com.zyk.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by zyk on 2017/10/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    public void insert(User user) {
        //redisUtil.set("test","this is test");
        String test = redisUtil.get("test");
        System.out.println("test = "+ test);
        userMapper.saveUser(user);
    }
}
