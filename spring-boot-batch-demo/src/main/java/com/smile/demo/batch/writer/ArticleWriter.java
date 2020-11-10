package com.smile.demo.batch.writer;

import com.smile.demo.batch.entity.ArticleDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author smile
 */
@Slf4j
public class ArticleWriter implements ItemWriter<ArticleDetail> {

    @Override
    public void write(List<? extends ArticleDetail> list) throws Exception {
        log.info("list的大小等于job中设置的chunkSize， size = {}", list.size());
        // TODO 此处可输出数据，比如输出到消息队列
        list.forEach(article -> log.info("输出测试，title:{}", article.getTitle()));
    }
}