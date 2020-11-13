package com.smile.demo.batch.Reader;

import com.smile.demo.batch.entity.Article;
import com.smile.demo.batch.jdbc.ArticleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smile
 */
@Component
public class ArticleReaderDemo {

    private final DataSource dataSource;

    public ArticleReaderDemo(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * 普通读取模式
     * - MySQL会将所有的纪录读到内存中
     * - 数据量大的话内存占用会很高
     */
    public JdbcCursorItemReader<Article> getArticle(String executedTime) {
        String lastExecutedTime = "";
        String sql = StringUtils.join("SELECT * FROM article WHERE event_occurred_time >= '",
                lastExecutedTime, "' AND event_occurred_time < '", executedTime, "'");
        return new JdbcCursorItemReaderBuilder<Article>()
                .dataSource(dataSource)
                .sql(sql)
                .fetchSize(10)
                .name("getArticle")
                .beanRowMapper(Article.class)
                .build();
    }

    /**
     * 分页读取模式
     * - 只要分页合理配置，内存占用可控
     */
    public JdbcPagingItemReader<Article> getArticlePaging(String executedTime) {
        String lastExecutedTime = "";
        Map<String, Object> parameterValues = new HashMap<>(2);
        parameterValues.put("startTime", lastExecutedTime);
        parameterValues.put("stopTime", executedTime);
        return new JdbcPagingItemReaderBuilder<Article>()
                .dataSource(dataSource)
                .name("getArticlePaging")
                .fetchSize(10)
                .parameterValues(parameterValues)
                .pageSize(10)
                .rowMapper(new ArticleMapper())
                .queryProvider(articleProvider())
                .build();
    }

    private PagingQueryProvider articleProvider() {
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("event_occurred_time", Order.ASCENDING);
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("title, content, event_occurred_time");
        provider.setFromClause("article");
        provider.setWhereClause("event_occurred_time >= :startTime AND event_occurred_time < :stopTime");
        provider.setSortKeys(sortKeys);
        return provider;
    }


    /**
     * 读取csv示例
     */
    public FlatFileItemReader<Article> readCsv() {
        return new FlatFileItemReaderBuilder<Article>()
                .name("articleCsvReader")
                .resource(new ClassPathResource("article.csv"))
                .delimited()
                .names(new String[] { "title", "content", "eventOccurredTime" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Article>() {
                    {
                        setTargetType(Article.class);
                    }
                }).build();
    }
}
