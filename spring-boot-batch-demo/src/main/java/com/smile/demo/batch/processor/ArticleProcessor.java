package com.smile.demo.batch.processor;

import com.smile.demo.batch.entity.Article;
import com.smile.demo.batch.entity.ArticleDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * batch 处理器
 * @author smile
 */
@Component
public class ArticleProcessor implements ItemProcessor<Article, ArticleDetail> {

    @Override
    public ArticleDetail process(Article data) throws Exception {
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(data, articleDetail);
        articleDetail.setSource("weibo");
        articleDetail.setDescription("这是一条来源于微博的新闻");
        return articleDetail;
    }
}
