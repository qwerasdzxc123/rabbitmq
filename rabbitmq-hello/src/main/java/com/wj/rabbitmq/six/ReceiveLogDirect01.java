package com.wj.rabbitmq.six;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wj.rabbitmq.utils.RabbitMqUtils;

public class ReceiveLogDirect01 {
    public static final String EXCHANGE_NAME = "direct_logs01";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //声明一个队列
        channel.queueDeclare("console01",false,false,false,null);
        channel.queueBind("console01",EXCHANGE_NAME,"info");
        channel.queueBind("console01",EXCHANGE_NAME,"warning");
        //接收到的消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogDirect01控制台打印接收到的消息:" + new String(message.getBody(),"UTF-8"));
        };
        //消费者取消消息回调接口
        channel.basicConsume("console01",true,deliverCallback,consumerTag -> {

        });

    }
}
