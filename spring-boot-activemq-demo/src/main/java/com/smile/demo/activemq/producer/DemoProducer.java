package com.smile.demo.activemq.producer;


import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送消息的简单示例
 * @author smile
 */
@RestController
public class DemoProducer {

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public DemoProducer(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @GetMapping("producer/demo")
    public String producerDemo() {
        jmsMessagingTemplate.convertAndSend("smile.event.demo", "producer send message demo");
        return "send message success";
    }
}
