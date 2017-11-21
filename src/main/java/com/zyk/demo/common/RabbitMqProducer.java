package com.zyk.demo.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by zyk on 2017/11/14.
 */
public class RabbitMqProducer {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel =connection.createChannel();
            channel.queueDeclare(QueueConstant.QUEUE_NAME,false,false,false,null);
            String message = "Hello, this is a message,hahaahh!!!!";
            channel.basicPublish("",QueueConstant.QUEUE_NAME,null,message.getBytes("UTF-8"));
            System.out.println("Produce send + '"+message+"'");
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
