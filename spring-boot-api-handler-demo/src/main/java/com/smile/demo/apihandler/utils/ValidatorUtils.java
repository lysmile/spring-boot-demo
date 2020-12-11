package com.smile.demo.apihandler.utils;

import com.smile.demo.apihandler.exception.ValidatorRuntimeException;
import org.springframework.validation.BindingResult;

/**
 * 参数验证通用方法提取
 * @author smile
 */
public class ValidatorUtils {

    /**
     * controller bindingResult处理
     * @param bindingResult 参数验证结果集
     */
    public static void handleBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidatorRuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
    }
}
