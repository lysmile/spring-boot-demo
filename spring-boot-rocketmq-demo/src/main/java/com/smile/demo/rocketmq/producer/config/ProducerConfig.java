package com.smile.demo.rocketmq.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.log.ClientLogger;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 程序启动时初始化Producer
 * @author yangjunqiang
 */
@Configuration
@Slf4j
public class ProducerConfig {

    private final ProducerProperties properties;

    public ProducerConfig(ProducerProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DefaultMQProducer getRocketMQProducer() throws MQClientException {
        setClientProperty();
        DefaultMQProducer producer = new DefaultMQProducer(properties.getGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setRetryTimesWhenSendAsyncFailed(properties.getRetryTimesWhenSendAsyncFailed());
        producer.setSendMsgTimeout(properties.getSendMsgTimeout());
        producer.start();
        log.info("*** producer has started! groupName:[{}], namesrvAddr:[{}] ***", properties.getGroupName(), properties.getNamesrvAddr());
        return producer;
    }

    private void setClientProperty() {
        System.setProperty(ClientLogger.CLIENT_LOG_ROOT, properties.getClientLogDir());
        System.setProperty(ClientLogger.CLIENT_LOG_LEVEL, properties.getClientLogLevel());
    }
}
