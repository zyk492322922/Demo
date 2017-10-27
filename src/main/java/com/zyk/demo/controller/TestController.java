package com.zyk.demo.controller;

import com.zyk.demo.common.ResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyk on 2017/10/26.
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping("aaa")
    @ResponseBody
    public Object index(){
        System.out.println("this is  aaaa");
        Map map = new HashMap();
        map.put("name","张三");
        map.put("age",12);
        return ResponseBuilder.buildNormalResponse(map);
    }
}
