package com.smile.demo.jwt.config;

import com.smile.demo.jwt.entity.CodeResult;
import com.smile.demo.jwt.exception.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口访问统一异常处理
 * @author smile
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandleConfig {


    @ExceptionHandler(Exception.class)
    public CodeResult exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("接口[{}]请求发生异常,错误信息:{}", request.getRequestURI(), e.getMessage());
        return new CodeResult("-1", "服务异常");
    }

    @ExceptionHandler(TokenException.class)
    public CodeResult exceptionHandler(HttpServletRequest request, TokenException e) {
        return new CodeResult("-2", e.getMessage());
    }
}