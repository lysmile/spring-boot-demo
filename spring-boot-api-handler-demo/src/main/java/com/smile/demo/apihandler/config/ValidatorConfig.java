package com.smile.demo.apihandler.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * 参数校验快速失败配置
 * - 此模式下只要发现一个参数不匹配便会快速返回错误
 * @author smile
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}