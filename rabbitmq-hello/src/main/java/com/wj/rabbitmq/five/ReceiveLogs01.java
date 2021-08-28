package com.wj.rabbitmq.five;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wj.rabbitmq.utils.RabbitMqUtils;


public class ReceiveLogs01 {
    //交换机的名称
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("ReceiveLogs01等待接收消息，吧接收消息打印到屏幕上.....");
        //接收到的消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("控制台打印接收到的消息:" + new String(message.getBody(),"UTF-8"));
        };
        //消费者取消消息回调接口
        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {

        });

    }
}
