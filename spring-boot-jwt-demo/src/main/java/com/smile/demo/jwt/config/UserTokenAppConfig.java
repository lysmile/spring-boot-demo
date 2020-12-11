package com.smile.demo.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author smile
 */
@Component
public class UserTokenAppConfig implements WebMvcConfigurer {


    @Autowired
    private UserTokenInterceptor userTokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenInterceptor)
                .addPathPatterns("/demo/**")
                .excludePathPatterns("/demo/login")
        ;
    }
}