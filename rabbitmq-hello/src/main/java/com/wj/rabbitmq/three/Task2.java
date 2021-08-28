package com.wj.rabbitmq.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.wj.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

public class Task2 {
    //队列名称
    public static final String Task_QUEUE_NAME = "ack_queue03";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        boolean durable = true;
        channel.queueDeclare(Task_QUEUE_NAME,durable,false,false,null);
        //从控制台输入信息
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String message = scanner.next();
            //设置生产者发送消息为为持久化消息（要求保存在磁盘上）保存在内存中  添加 MessageProperties.PERSISTENT_TEXT_PLAIN 属性
            channel.basicPublish("",Task_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
            System.out.println("生产者发出信息" + message);
        }
    }
}
