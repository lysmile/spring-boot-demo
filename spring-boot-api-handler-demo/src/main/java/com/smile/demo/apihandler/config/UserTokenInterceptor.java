package com.smile.demo.apihandler.config;

import com.smile.demo.apihandler.exception.TokenException;
import com.smile.demo.apihandler.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * - 验证token
 * @author smile
 */
@Component
@Slf4j
public class UserTokenInterceptor implements HandlerInterceptor {

    private final JwtUtils jwtUtils;

    public UserTokenInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("！！进入拦截器方法");
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new TokenException("请传入正确的token");
        }
        jwtUtils.verify(token);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}