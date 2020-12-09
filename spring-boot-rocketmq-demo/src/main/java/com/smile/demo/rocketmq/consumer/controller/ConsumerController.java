package com.smile.demo.rocketmq.consumer.controller;

import com.smile.demo.rocketmq.consumer.ConsumerManager;
import com.smile.demo.rocketmq.consumer.entity.SubscriptionDto;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smile
 */
@RestController
public class ConsumerController {


    @PostMapping("subscribe")
    public String subscribe(@RequestBody SubscriptionDto subscriptionDto) {
        try {
            ConsumerManager.getInstance().subscribe(subscriptionDto.getTopic(), subscriptionDto.getTags());
        } catch (MQClientException e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    @PostMapping("unsubscribe")
    public String unsubscribe(@RequestBody SubscriptionDto subscriptionDto) {
        try {
            ConsumerManager.getInstance().unsubscribe(subscriptionDto.getTopic(), subscriptionDto.getTags());
        } catch (MQClientException e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

}
