package com.smile.demo.apihandler.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 * @author smile
 */
@Component
public class UserTokenAppConfig implements WebMvcConfigurer {


    private final UserTokenInterceptor userTokenInterceptor;

    public UserTokenAppConfig(UserTokenInterceptor userTokenInterceptor) {
        this.userTokenInterceptor = userTokenInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenInterceptor)
                // 配置需要被拦截的接口
                // 注意此处是controller中的路径，配置文件中的server.servlet.context-path不能在此处加上，否则拦截器不会生效
                .addPathPatterns("/v0.1/**")
                // 不被拦截的接口
                .excludePathPatterns("/token/get")
        ;
    }
}