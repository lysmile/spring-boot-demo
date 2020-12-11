package com.smile.demo.apihandler.enums;

/**
 * 错误码
 * 0 ：成功
 * 1*：业务内自定义错误码
 * 2*：
 * 3*：
 * 4*：网络错误
 * 5*：系统内部错误，包含代码执行异常等
 * @author smile
 *
 */
public enum ResponseEnum {

	/**
	 * 请求成功
	 */
	SUCCESS("0", "success"),
	/**
	 * 请求参数错误
	 */
	REQUEST_PARAM_ERROR("1001", "参数错误"),
	/**
	 * token验证失败
	 */
	TOKEN_ERROR("1002", "token验证失败"),
	/**
	 * 服务内部异常，包括代码执行错误及一些不确定错误
	 */
	SERVICE_ERROR("5001", "服务异常，请稍后再试！")
	
	
	;
	
	private String code;
	private String msg;
	
	ResponseEnum(String code, String msg) {
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
