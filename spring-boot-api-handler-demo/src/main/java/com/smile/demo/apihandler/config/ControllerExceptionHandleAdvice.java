package com.smile.demo.apihandler.config;

import com.smile.demo.apihandler.entity.CodeResult;
import com.smile.demo.apihandler.enums.ResponseEnum;
import com.smile.demo.apihandler.exception.TokenException;
import com.smile.demo.apihandler.exception.ValidatorRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口访问统一异常处理
 * - 当程序报错时，由此类进行统一处理
 * - 防止将程序内部错误暴露给用户
 * @author smile
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandleAdvice {

    @ExceptionHandler(ValidatorRuntimeException.class)
    public CodeResult validationExceptionHandler(HttpServletRequest request, ValidatorRuntimeException e) {
        if (log.isDebugEnabled()) {
            log.error("接口[{}]请求发生[接口检验异常],错误信息:{}", request.getRequestURI(), e.getMessage());
        }
        return new CodeResult(ResponseEnum.REQUEST_PARAM_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CodeResult exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("接口[{}]请求发生异常,错误信息:{}", request.getRequestURI(), e.getMessage());
        return new CodeResult(ResponseEnum.SERVICE_ERROR);
    }

    @ExceptionHandler(TokenException.class)
    public CodeResult exceptionHandler(HttpServletRequest request, TokenException e) {
        if (log.isDebugEnabled()) {
            log.error("接口[{}]请求发生[token验证异常],错误信息:{}, token:{}", request.getRequestURI(), e.getMessage(), request.getHeader("token"));
        }
        return new CodeResult(ResponseEnum.TOKEN_ERROR.getCode(), e.getMessage());
    }
}