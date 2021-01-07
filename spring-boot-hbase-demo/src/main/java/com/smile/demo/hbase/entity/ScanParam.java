package com.smile.demo.hbase.entity;

import lombok.Data;

import java.util.List;

/**
* hbase 批量获取数据的基础参数
* @author smile
*/
@Data
public class ScanParam {
	
	private String tableName;

	private String colFamily;

	private List<String> columns;

	private String startRow;
	
	private String stopRow;

	private int pageSize;

	private Boolean reversed;
}
