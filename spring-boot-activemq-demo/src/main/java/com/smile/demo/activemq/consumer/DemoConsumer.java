package com.smile.demo.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者示例
 * @author smile
 */
@Component
public class DemoConsumer {


    /**
     * queueListener 对应 ActiveMqConfig中的Bean
     */
    @JmsListener(destination="smile.event.demo", containerFactory = "queueListener")
    public void receiveData(String message) {
        System.out.println("!!receive message: " + message);
    }
}