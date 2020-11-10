package com.smile.demo.batch.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 此类是为了Springboot batch的任务管理使用内存模式
 * @author smile
 */
@Component
public class BatchInMemoryConfig extends DefaultBatchConfigurer {
    @Override
    public void setDataSource(DataSource dataSource) {
    }
}