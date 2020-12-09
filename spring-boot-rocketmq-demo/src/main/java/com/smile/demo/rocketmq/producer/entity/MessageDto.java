package com.smile.demo.rocketmq.producer.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author smile
 */
@Data
@ApiModel(description = "消息发送参数体")
public class MessageDto {

    @ApiModelProperty(name = "topic", value = "topic", example = "springboot")
    private String topic;
    @ApiModelProperty(name = "tag", value = "tag", example = "demo")
    private String tag;
    @ApiModelProperty(name = "messageBody", value = "待发送的消息体", example = "test")
    private String messageBody;

}
