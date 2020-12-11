package com.smile.demo.apihandler.exception;

/**
 * 自定义异常-参数校验
 * @author smile
 *
 */
public class ValidatorRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -1L;

	public ValidatorRuntimeException() {
		super();
	}

	public ValidatorRuntimeException(String message) {
		super(message);
	}

	public ValidatorRuntimeException(Throwable cause) {
		super(cause);
	}

	public ValidatorRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
