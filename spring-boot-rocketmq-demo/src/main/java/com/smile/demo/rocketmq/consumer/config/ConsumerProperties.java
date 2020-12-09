package com.smile.demo.rocketmq.consumer.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * RocketMQ consumer配置项
 * @author smile
 */
@Component
@ConfigurationProperties(prefix = "rocketmq.consumer")
@Data
public class ConsumerProperties {

    private String namesrvAddr;
    private int threadMax;
    private int threadMin;
    private String groupName;
    private String clientLogDir;
    private String clientLogLevel;
}
