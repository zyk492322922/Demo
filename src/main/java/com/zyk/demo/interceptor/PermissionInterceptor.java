package com.zyk.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.zyk.demo.common.ResponseBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zyk on 2017/10/30.
 */
public class PermissionInterceptor implements HandlerInterceptor{
    //  preHandle()方法在业务处理器处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        // 不拦截跨域握手方法
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return Boolean.TRUE;
        }

        String token = StringUtils.isBlank(request.getHeader("token"))? request.getParameter("token"):"";
        if (StringUtils.isBlank(token)){
            ResponseBuilder result = ResponseBuilder.buildErrorResponse("缺少token参数");
            PrintWriter out =  response.getWriter();
            out.append(JSON.toJSONString(result));
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    //   postHandle()方法在业务处理器处理请求之后被调用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    // afterCompletion()方法在DispatcherServlet完全处理完请求后被调用
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
