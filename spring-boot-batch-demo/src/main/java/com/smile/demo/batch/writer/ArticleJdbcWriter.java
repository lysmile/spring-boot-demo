package com.smile.demo.batch.writer;

import com.smile.demo.batch.entity.ArticleDetail;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author smile
 */
@Component
public class ArticleJdbcWriter {

    private final DataSource dataSource;

    public ArticleJdbcWriter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcBatchItemWriter<ArticleDetail> writer() {
        return new JdbcBatchItemWriterBuilder<ArticleDetail>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO article_detail (title, content, event_occurred_time, source, description) VALUES (:title, :content, :eventOccurredTime, :source, :description)")
                .dataSource(dataSource)
                .build();
    }
}
