package com.smile.demo.jwt.entity;


import lombok.Data;

/**
 * 接口返回封装实体
 * @author yangjunqiang
 */
@Data
public class CodeResult {

	private String errCode;
	private String errMsg;
	private Object data;
	
	public CodeResult() { }
	
	public CodeResult(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public CodeResult(String errCode, String errMsg, Object data) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.data = data;
	}
}
