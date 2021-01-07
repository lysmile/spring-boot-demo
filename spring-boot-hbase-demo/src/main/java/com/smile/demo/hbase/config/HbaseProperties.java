package com.smile.demo.hbase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 从配置文件中读取hbase配置信息
 * 配置文件格式:hbase.config.*=xxx
 * @author smile
 */
@ConfigurationProperties(prefix = "hbase")
public class HbaseProperties {

	private Map<String, String> config;

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}
}