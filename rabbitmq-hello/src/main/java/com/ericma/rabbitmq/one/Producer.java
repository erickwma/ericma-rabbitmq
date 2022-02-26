package com.ericma.rabbitmq.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String QUEUE_NAME = "myQueue";
    private final static String message = "hello world";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Program Start!");
        // create connection factory
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // configuration
        connectionFactory.setHost("localhost");
//        connectionFactory.setHost("guest");
//        connectionFactory.setHost("guest");

        // create connection
        Connection connection = connectionFactory.newConnection();
        // create channel

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println("Message sent!");
    }

}
