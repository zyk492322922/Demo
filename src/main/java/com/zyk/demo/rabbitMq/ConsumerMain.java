package com.zyk.demo.rabbitMq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zyk on 2018/1/8.
 * 用main方法启动消费者
 */
public class ConsumerMain {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:spring/spring-rabbitmq-consumer.xml");
    }
}
