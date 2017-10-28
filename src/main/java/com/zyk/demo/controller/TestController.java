package com.zyk.demo.controller;

import com.zyk.demo.common.ResponseBuilder;
import com.zyk.demo.entity.User;
import com.zyk.demo.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyk on 2017/10/26.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserService userService;

    @RequestMapping("aaa")
    @ResponseBody
    public Object index(){
        System.out.println("this is  aaaa");
        Map map = new HashMap();
        map.put("name","张三");
        map.put("age",12);
        User user = new User();
        user.setName("咱三");
        user.setAge("1232");
        user.setUserName("aaaa");
        userService.insert(user);
        return ResponseBuilder.buildNormalResponse(map);
    }
}
