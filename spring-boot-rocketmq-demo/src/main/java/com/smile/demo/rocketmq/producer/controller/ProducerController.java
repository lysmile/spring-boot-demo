package com.smile.demo.rocketmq.producer.controller;

import com.smile.demo.rocketmq.producer.ProducerUtils;
import com.smile.demo.rocketmq.producer.entity.MessageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.*;

/**
 * 测试消息发送的接口
 * @author smile
 */
@RestController
@Api(value = "Producer测试接口", tags="Producer测试接口")
public class ProducerController {

    private final ProducerUtils producerUtils;

    public ProducerController(ProducerUtils producerUtils) {
        this.producerUtils = producerUtils;
    }

    @GetMapping("createTopic/{topic}")
    @ApiOperation(value = "创建topic")
    @ApiImplicitParam(name = "topic", value = "待创建的topic", required = true, dataType = "String", paramType = "query")
    public String createTopic(@PathVariable String topic) throws MQClientException {
        producerUtils.createTopic(topic);
        return "topic:[" + topic + "] create success";
    }


    @PostMapping("send/oneway")
    @ApiOperation(value = "一次性消息")
    @ApiImplicitParam(name = "message", value = "参数体", dataType = "MessageDto", paramType = "body")
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
