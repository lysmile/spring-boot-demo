package com.smile.demo.hbase.entity;

import lombok.Data;

import java.util.Map;

/**
 *
 * @author smile
 */
@Data
public class DataVolume {

	private String rowkey;
	
	private Map<String, String> data;
}
