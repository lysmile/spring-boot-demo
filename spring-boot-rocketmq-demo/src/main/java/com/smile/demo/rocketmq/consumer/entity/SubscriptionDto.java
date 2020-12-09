package com.smile.demo.rocketmq.consumer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author smile
 */
@Data
@ApiModel(description = "事件订阅参数体")
public class SubscriptionDto {


    @ApiModelProperty(name = "topic", value = "topic", example = "demo")
    private String topic;
    @ApiModelProperty(name = "tags", value = "tags", example = "demo")
    private String tags;
}
