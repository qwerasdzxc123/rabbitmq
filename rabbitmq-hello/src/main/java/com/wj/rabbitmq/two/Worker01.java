package com.wj.rabbitmq.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wj.rabbitmq.utils.RabbitMqUtils;

public class Worker01 {
    //队列名称
    public static final String QUEUE_NAME = "hello";
    //接收消息
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //消息接收
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println("接收到的消息" + new String(message.getBody()));
        };

        //消息接收被取消 执行下面内容
        CancelCallback cancelCallback = (consumeTag)->{
            System.out.println(consumeTag + "消息者取消消费接口回调逻辑");
        };

        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         * 4.消息者取录消费者回调
         */
        System.out.println("C2等待接收消息。。。。");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
