package com.zyk.demo.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义annotation
 */
@Target(ElementType.METHOD)  // 说明了Annotation所修饰的对象范围  mehtod 用户修饰方法
@Retention(RetentionPolicy.RUNTIME) //  在runtime运行周期有效
public @interface DemoAnnotation {
}
