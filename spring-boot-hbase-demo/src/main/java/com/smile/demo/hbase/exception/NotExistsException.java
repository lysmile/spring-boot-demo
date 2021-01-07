package com.smile.demo.hbase.exception;

/**
 * 自定义异常-不存在异常
 * @author smile
 *
 */
public class NotExistsException extends Exception{

	private static final long serialVersionUID = 4157158579954448650L;

	public NotExistsException() {
		super();
	}
	
	public NotExistsException(String message) {
		super(message);
	}
	
	public NotExistsException(Throwable cause) {
		super(cause);
	}
	
	public NotExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
