package com.zyk.demo.common.aspect;

import com.zyk.demo.common.annotation.DemoAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 使用注解实现aop
 * Around , Before , After
 * Around: 是在方法执行前后都执行一段代码，所以需要用proceed(),
 * 执行接口方法, before和after都不用
 */

@Aspect
@Component
public class DemoAOP {

    // 扫描controller包下面所有的方法并且有DemoAnnotation标签,注意此处的demoAno,直接用aop实现方法中的形参
    private static final String DEMO_EXECUTION = "execution(* com.zyk.demo.controller.*.*(..)) && @annotation(demoAno)";

    @Around(DEMO_EXECUTION)
    public Object execute(ProceedingJoinPoint pjp, DemoAnnotation demoAno) throws  Throwable{
        System.out.println(">>>>>>>this is aop method<<<<<<<<");
        Object result = pjp.proceed();
        return result;
    }
}
