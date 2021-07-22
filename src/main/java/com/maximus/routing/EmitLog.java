package com.maximus.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
    private static final String EXCHANGE_NAME = "directLogs";
    private static final String severity = "error";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            String message = "emit error logs";
            //使用随机队列名
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println("[send]" + message + "---");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
