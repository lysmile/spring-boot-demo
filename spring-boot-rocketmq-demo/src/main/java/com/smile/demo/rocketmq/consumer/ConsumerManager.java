package com.smile.demo.rocketmq.consumer;

import com.smile.demo.rocketmq.consumer.config.ConsumerProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smile
 */
@Slf4j
public class ConsumerManager {

    private static final ConsumerManager MANAGER = new ConsumerManager();
    private static final String TAGS_SEP = "||";
    private static Map<String, String> subscription = new HashMap<String, String>(8) {
        {
            // 订阅与取消订阅的事件
            put("sys", "subscribe||unsubscribe");
        }
    };

    private static DefaultMQPushConsumer consumer;


    /**
     * 单例，不允许外界主动实例化
     */
    private ConsumerManager() {
    }

    public static ConsumerManager getInstance() {
        return MANAGER;
    }

    public void initConsumer(ConsumerProperties properties) throws MQClientException {
        // 设置client日志信息, producer初始化时已配置，此处不再配置
//        System.setProperty(ClientLogger.CLIENT_LOG_ROOT, properties.getClientLogDir());
//        System.setProperty(ClientLogger.CLIENT_LOG_LEVEL, properties.getClientLogLevel());

        consumer = new DefaultMQPushConsumer(properties.getGroupName());
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setConsumeThreadMax(properties.getThreadMax());
        consumer.setConsumeThreadMin(properties.getThreadMin());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        log.info("consumer topic and tags : {}", subscription);
        for (Map.Entry<String, String> entry : subscription.entrySet()) {
            consumer.subscribe(entry.getKey(), entry.getValue());
        }
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            try {
                MessageExt msg = msgs.get(0);
                String msgBody = new String(msg.getBody(), "utf-8");
                log.info("receive message, messageId:[{}], messageBody:{}， topic:[{}], tag:[{}]",
                        msg.getMsgId(), msgBody, msg.getTopic(), msg.getTags());
                log.info("delay: [{}] ms", (System.currentTimeMillis() - msg.getBornTimestamp()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                // 如果执行异常，则稍后会重新消费
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        log.info("consumer has started! NamesrvAddr:[{}], groupName:[{}]", properties.getNamesrvAddr(), properties.getGroupName());
    }


    public void subscribe(String topic, String tags) throws MQClientException {
        if (subscription.containsKey(topic)) {
            tags = StringUtils.join(subscription.get(topic), TAGS_SEP, tags);
        }
        consumer.subscribe(topic, tags);
        subscription.put(topic, tags);
        log.info("!!刷新订阅, {}", subscription);
    }

    public void unsubscribe(String topic, String tags) throws MQClientException {
        if (!subscription.containsKey(topic)) {
            log.error("topic is not found");
            return;
        }
        String[] unsubscribeTags = tags.trim().split("\\|\\|");
        String[] existingTags = subscription.get(topic).trim().split("\\|\\|");
        log.info("unsubscribeTags: {}, existingTags: {}", unsubscribeTags, existingTags);
        StringBuilder newTagsBuilder = new StringBuilder();
        for (String existingTag : existingTags) {
            if (!ArrayUtils.contains(unsubscribeTags, existingTag)) {
                newTagsBuilder.append(existingTag).append(TAGS_SEP);
            }
        }

        if (tags.length() == 0) {
            consumer.unsubscribe(topic);
            return;
        }

        String newTags = newTagsBuilder.substring(0, newTagsBuilder.length() - 2);
        log.info("newTags: {}", newTagsBuilder);
        consumer.subscribe(topic, newTags);
        subscription.put(topic, newTags);
        log.info("!!取消订阅，新的订阅列表, {}", subscription);
    }

}
