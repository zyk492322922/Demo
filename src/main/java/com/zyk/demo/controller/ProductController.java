package com.zyk.demo.controller;

import com.zyk.demo.common.ResponseBuilder;
import com.zyk.demo.param.ProductParam;
import com.zyk.demo.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zyk on 2018/2/10.
 */
@Controller
@RequestMapping("/product")
public class ProductController {


    @Resource
    private ProductService productService;

    @RequestMapping(value = "getList",method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(@RequestBody ProductParam param){
        if (param == null){
            return ResponseBuilder.buildErrorResponse("参数错误");
        }
        return ResponseBuilder.buildNormalResponse(productService.getList(param));
    }

}
