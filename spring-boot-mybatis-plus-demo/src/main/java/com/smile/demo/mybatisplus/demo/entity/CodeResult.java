package com.smile.demo.mybatisplus.demo.entity;


import com.smile.demo.mybatisplus.enums.ResponseEnum;
import lombok.Data;

/**
 * 接口返回封装实体
 * @author Smile
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
	
	public CodeResult(ResponseEnum response) {
		this.errCode = response.getCode();
		this.errMsg = response.getMsg();
	}
	
	public CodeResult(ResponseEnum response, Object data) { 
		this.errCode = response.getCode();
		this.errMsg = response.getMsg();
		this.data = data;
	}
	
}
