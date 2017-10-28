package com.zyk.demo.dao;

import com.zyk.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by zyk on 2017/10/29.
 */

public interface UserMapper {

    void saveUser(@Param("entity") User user);
}
