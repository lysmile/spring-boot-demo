package com.smile.demo.rocketmq.consumer.controller;

import com.smile.demo.rocketmq.consumer.ConsumerManager;
import com.smile.demo.rocketmq.consumer.entity.SubscriptionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smile
 */
@RestController
@Api(value = "consumer测试接口", tags="consumer测试接口")
public class ConsumerController {


    @PostMapping("subscribe")
    @ApiOperation(value = "订阅事件")
    @ApiImplicitParam(name = "subscriptionDto", value = "参数体", dataType = "SubscriptionDto", paramType = "body")
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
    @ApiOperation(value = "取消订阅")
    @ApiImplicitParam(name = "subscriptionDto", value = "参数体", dataType = "SubscriptionDto", paramType = "body")
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
