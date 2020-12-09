package com.smile.demo.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * RocketMQ producer工具类
 * @author yangjunqiang
 */
@Component
@Slf4j
public class ProducerUtils {

    private final DefaultMQProducer producer;

    public ProducerUtils(DefaultMQProducer producer) {
        this.producer = producer;
    }

    @Value("${rocketmq.producer.brokerName}")
    private String brokerName;


    /**
     * 创建topic
     */
    public void createTopic(String topic) throws MQClientException {
        producer.createTopic(brokerName, topic, 8);
    }


    /**
     * 同步消息
     * - 会有返回值，能够确认是否发送成功
     * - 效率较低
     */
    public void sendSyncMsg(String topic, String tags, String msgBody) throws Exception {
        SendResult sr = producer.send(buildMsg(topic, tags, msgBody));
        if (sr.getSendStatus() != SendStatus.SEND_OK) {
            if (log.isDebugEnabled()) {
                log.error("send failed，msg：{}", msgBody);
            }
        }
    }

    /**
     * 只发送一次，不管是否成功
     */
    public void sendOnewayMsg(String topic, String tags, String msgBody) throws Exception {
        producer.sendOneway(buildMsg(topic, tags, msgBody));
    }

    /**
     * 延迟消息
     * messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
     */
    public void sendDelayMsg(String topic, String tags, String msgBody, int level) throws Exception {
        Message msg = buildMsg(topic, tags, msgBody);
        // level是数值，1代表上面的1S， 2代表5S ……
        msg.setDelayTimeLevel(level);
        producer.sendOneway(buildMsg(topic, tags, msgBody));
    }

    /**
     * 异步消息
     */
    public void sendAsyncMsg(String topic, String tags, String msgBody) throws Exception {
        Message msg = new Message(topic, tags, msgBody.getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 消息发送成功
                log.debug("send success，msg：{}", msgBody);
            }

            @Override
            public void onException(Throwable e) {
                e.fillInStackTrace();
                log.error("send failed，msg：{}, error info：{}", msgBody, e.getMessage());
            }
        });
    }


    private Message buildMsg(String topic, String tags, String msgBody) throws UnsupportedEncodingException {
        return new Message(topic, tags, msgBody.getBytes(RemotingHelper.DEFAULT_CHARSET));
    }
}
