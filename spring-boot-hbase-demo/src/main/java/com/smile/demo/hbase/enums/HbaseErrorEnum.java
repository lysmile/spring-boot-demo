package com.smile.demo.hbase.enums;

/**
 * 错误码
 * 0 ：成功
 * 1*：业务内自定义错误码
 * 2*：
 * 3*：
 * 4*；网络错误
 * 5*：系统内部错误，包含代码执行异常等
 * @author smile
 *
 */
public enum HbaseErrorEnum {

	/**
	 * 目标表不存在
	 */
	TABLE_NOT_EXISTS("1001", "目标表不存在"),
	/**
	 * 创建表失败
	 */
	TABLE_CREATE_FAILED("1002", "创建表失败"),
	/**
	 * 请求参数错误
	 */
	PARAM_ERROR("1003", "参数错误"),
	/**
	 * 服务内部异常，包括代码执行错误及一些不确定错误
	 */
	SERVICE_ERROR("5001", "服务异常，请稍后再试！")
	
	
	;
	
	private String code;
	private String msg;
	
	HbaseErrorEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
