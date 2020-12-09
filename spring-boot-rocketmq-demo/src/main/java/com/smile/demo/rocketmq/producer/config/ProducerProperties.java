package com.smile.demo.rocketmq.producer.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * RocketMQ Producer 配置项
 * @author yangjunqiang
 */
@Component
@ConfigurationProperties(prefix = "rocketmq.producer")
@Data
public class ProducerProperties {

    private String clientLogDir;
    private String clientLogLevel;
    private String namesrvAddr;
    private String groupName;
    private int retryTimesWhenSendAsyncFailed;
    private int sendMsgTimeout;

}
