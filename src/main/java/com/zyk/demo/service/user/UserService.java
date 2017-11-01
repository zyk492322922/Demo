package com.zyk.demo.service.user;

import com.zyk.demo.entity.User;
import com.zyk.demo.param.UserParam;

/**
 * Created by zyk on 2017/10/29.
 */
public interface UserService {

    public void insert(User user);

    public User getById(int id);

    public void update(UserParam param);
}
