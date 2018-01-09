package com.zyk.demo.rabbitMq;

import com.zyk.demo.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zyk on 2018/1/8.
 * 用main方法启动生产者
 */
public class ProduceMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-rabbitmq-produce.xml");
        AmqpTemplate amqpTemplate = context.getBean(RabbitTemplate.class);
        User user = new User();
        user.setUserName("zhangyk2");
        user.setRealName("张云凯2");
        user.setPassword("345432456");
        amqpTemplate.convertAndSend(user);
    }
}
