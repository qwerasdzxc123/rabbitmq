package com.wj.rabbitmq.two;

import com.rabbitmq.client.Channel;
import com.wj.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

public class Task01 {
    //队列名称
    public static final String QUEUE_NAME = "hello";
    //发送大量的消息
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //从控制台接收信息
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String message = scanner.next();
            System.out.println("发送消息完成"+message);
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        }
    }
}
