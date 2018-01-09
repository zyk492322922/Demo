package com.zyk.demo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zyk on 2018/1/8.
 */
public class ConsumerMain {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:spring/spring-rabbitmq-consumer.xml");
    }
}
