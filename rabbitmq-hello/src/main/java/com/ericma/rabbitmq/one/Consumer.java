/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ericma.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private final static String QUEUE_NAME = "myQueue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");

        //establish a connection by factory method provided the library
        Connection connection = connectionFactory.newConnection();
        // establish a channel
        Channel channel = connection.createChannel();
        // the 3rd argument should be a callback that handles the case for failed consumptions

        // implement callbacks
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(message);
            System.out.println(new String(message.getBody()));
        };
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("Message consumption interrupted!");
        };

        // simple call for consumption
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);

    }
}
