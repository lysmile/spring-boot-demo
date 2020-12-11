package com.smile.demo.jwt.exception;

/**
 * 自定义异常-token验证
 * @author smile
 *
 */
public class TokenException extends Exception{

	private static final long serialVersionUID = 4157158579954448650L;

	public TokenException() {
		super();
	}

	public TokenException(String message) {
		super(message);
	}

	public TokenException(Throwable cause) {
		super(cause);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
