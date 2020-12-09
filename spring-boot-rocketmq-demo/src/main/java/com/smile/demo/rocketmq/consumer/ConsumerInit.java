package com.smile.demo.rocketmq.consumer;

import com.smile.demo.rocketmq.consumer.config.ConsumerProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author yangjunqiang
 */
@Component
public class ConsumerInit implements CommandLineRunner {

    private final ConsumerProperties properties;

    public ConsumerInit(ConsumerProperties properties) {
        this.properties = properties;
    }

    @Override
    public void run(String... args) throws Exception {
        ConsumerManager.getInstance().initConsumer(properties);
    }
}
