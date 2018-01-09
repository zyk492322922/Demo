package com.zyk.demo.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by zyk on 2018/1/8.
 */
public class QueueConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("QueueConsumer 收到消息: " + new String(message.getBody()));
    }
}
