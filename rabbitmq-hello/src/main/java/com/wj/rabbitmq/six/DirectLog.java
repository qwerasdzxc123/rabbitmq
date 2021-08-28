package com.wj.rabbitmq.six;

import com.rabbitmq.client.Channel;
import com.wj.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

public class DirectLog {
    //交换机的名称
    public static final String EXCHANGE_NAME = "direct_logs01";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"info",null,message.getBytes("UTF-8"));
            System.out.println("生产者发出的消息" + message);
        }
    }
}
