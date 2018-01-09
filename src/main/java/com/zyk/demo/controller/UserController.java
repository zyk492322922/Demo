package com.zyk.demo.controller;

import com.zyk.demo.common.ResponseBuilder;
import com.zyk.demo.common.annotation.DemoAnnotation;
import com.zyk.demo.entity.User;
import com.zyk.demo.param.UserParam;
import com.zyk.demo.service.user.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyk on 2017/10/26.
 *  用户模块
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(@RequestBody UserParam param){
        User user = User.builder().userName(param.getUserName()).realName(param.getRealName()).password(param.getPassword()).build();
        userService.insert(user);
        return ResponseBuilder.buildNormalResponse();
    }

    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody UserParam param){
        userService.update(param);
        return ResponseBuilder.buildNormalResponse();
    }


    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    @DemoAnnotation
    public Object testaop(){
        System.out.println(">>>>>>this is test method<<<<<<");
        return ResponseBuilder.buildNormalResponse();
    }

    @RequestMapping(value = "test2",method = RequestMethod.GET)
    @ResponseBody
    public Object testNotAop(){
        System.out.println(">>>>>>this is test not aop method<<<<<<");
        return ResponseBuilder.buildNormalResponse();
    }

    @RequestMapping(value = "testMq",method = RequestMethod.GET)
    @ResponseBody
    public Object testMq(){
        User user = User.builder().userName("123").realName("aaa").password("123456").build();
        amqpTemplate.convertAndSend(user);
        System.out.println("mq 发送success>>>>>>>>");
        return ResponseBuilder.buildNormalResponse();
    }



}
