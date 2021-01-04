package com.smile.demo.resttemplate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * HttpClient配置项
 * @author smile
 */
@Component
@ConfigurationProperties(prefix = "http.pool")
@Data
public class HttpClientProperties {


    /**
     * 最大连接数
     */
    private Integer maxTotal;
    /**
     * 路由是对最大连接数的细分
     * 每个路由基础的连接数
     */
    private Integer defaultMaxPerRoute;
    /**
     * 连接超时时间
     */
    private Integer connectTimeout;
    /**
     * 从连接池中获取连接的超时时间
     */
    private Integer connectionRequestTimeout;
    /**
     * 服务器返回数据(response)的时间
     */
    private Integer socketTimeout;
    /**
     * 可用空闲连接过期时间
     * 重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
     */
    private Integer validateAfterInactivity;
}