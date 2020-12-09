package com.smile.demo.rocketmq.producer.controller;

import com.smile.demo.rocketmq.producer.ProducerUtils;
import com.smile.demo.rocketmq.producer.entity.MessageDto;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.*;

/**
 * 测试消息发送的接口
 * @author yangjunqiang
 */
@RestController
public class ProducerController {

    private final ProducerUtils producerUtils;

    public ProducerController(ProducerUtils producerUtils) {
        this.producerUtils = producerUtils;
    }

    @GetMapping("createTopic/{topic}")
    public String createTopic(@PathVariable String topic) throws MQClientException {
        producerUtils.createTopic(topic);
        return topic + "create success";
    }


    @PostMapping("send/oneway")
    public String send(@RequestBody MessageDto message) {
        try {
            producerUtils.sendOnewayMsg(message.getTopic(), message.getTag(), message.getMessageBody());
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }
}
