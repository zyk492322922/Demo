package com.zyk.demo.service.user;

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
    public void insert(User user) {
        userMapper.saveUser(user);
    }
}
