package com.smile.demo.batch.jdbc;

import com.smile.demo.batch.entity.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库映射文件
 * @author smile
 */
public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setEventOccurredTime(rs.getString("event_occurred_time"));
        return article;
    }
}
